package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import me.naingaungluu.formconductor.annotations.FieldValidation
import me.naingaungluu.formconductor.syntax.AnnotationSyntaxProcessor
import me.naingaungluu.formconductor.syntax.SyntaxProcessor
import me.naingaungluu.formconductor.syntax.SyntaxResult
import me.naingaungluu.formconductor.syntax.getErrorMessage
import me.naingaungluu.formconductor.validation.FieldValidator
import me.naingaungluu.formconductor.validation.ValidationRule
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.jvmName

/**
 * An implementation of the [Form] interface
 *
 * @param T type of form Data class
 * @property formClass [KClass] reference of form Data
 */
class FormImpl<T : Any>(
    private val formClass: KClass<T>
) : Form<T> {

    /**
     * Stores a map of fields to it's [FormField] objects
     */
    private val fieldMap: Map<KProperty1<T, *>, FormField<Any>>

    /**
     * Proxy form data flow
     */
    override val formDataStream: Flow<FormResult<T>>

    init {
        // Check requirements for form class
        require(constructorRequirementSatisfied(formClass))

        // Fetch field list from class reference
        val fields = formClass.memberProperties.toSet()

        // Check Syntax for each field
        val syntaxProcessor: SyntaxProcessor = AnnotationSyntaxProcessor()
        fields.forEach {
            val syntaxResult = syntaxProcessor.process(it)
            require(syntaxResult is SyntaxResult.Success) {
                (syntaxResult as SyntaxResult.Error).getErrorMessage(formClass, it)
            }
        }

        // Assign each field a [FormField] object
        fieldMap = fields.associateWith { initializeField(it) }

        // Observe a list of upstream flows from each of the [FormField] object
        formDataStream = combine(
            fieldMap.values.map { it.valueStream.asSharedFlow() }
        ) { _ ->

            /*
                Fetch field results (Note: We allow FieldResult.NoInput as successful validation)
             */
            val resultStreams = fieldMap.values.map { it.resultStream.value }
            val formSuccess = resultStreams.all {
                it is FieldResult.Success || it is FieldResult.NoInput
            }

            if (formSuccess) {
                val formData = constructFormData()
                FormResult.Success(formData)
            } else {
                // Fetch failed rules in case of error
                val failedRules = resultStreams
                    .filterIsInstance<FieldResult.Error>()
                    .map { it.failedRule }

                FormResult.Error(failedRules.toSet())
            }
        }
    }

    /**
     * Fetches and returns the associated [FormField] object of the given Kotlin property
     *
     * @param V data type of field
     * @param fieldClass kotlin property reference of the field
     * @return [FormField] instance to work with the form
     */
    override fun <V : Any> registerField(fieldClass: KProperty1<T, V>): FormField<V> {
        return getField(fieldClass)
    }

    /**
     * queries the [FormField] object from the fieldMap
     *
     * @param V data type of the field
     * @param fieldClass kotlin property reference of the field
     * @return [FormField] instance to work with the form
     */
    @Suppress("UNCHECKED_CAST")
    private fun <V : Any> getField(fieldClass: KProperty1<T, V>): FormField<V> {
        return fieldMap[fieldClass]!! as FormField<V>
    }

    /**
     * Sets the value of the field through the form instance
     *
     * @param V data type of the field
     * @param fieldClass kotlin property reference of the field
     * @param value input value
     */
    override fun <V : Any> setField(
        fieldClass: KProperty1<T, V>,
        value: V
    ) {
        val field = getField(fieldClass)
        field.setField(value)
    }

    /**
     * Checks if constructor requirement are satisfied for form class
     *
     * @param formDataClass kotlin class reference of form data
     * @return
     */
    private fun constructorRequirementSatisfied(formDataClass: KClass<T>): Boolean {
        val primaryConstructor = formClass.primaryConstructor

        val hasPrimaryConstructor = (primaryConstructor != null)

        // All constructor parameter should be optional to allow optional input values
        val hasDefaultConstructorParameters = primaryConstructor?.parameters?.all {
            it.isOptional
        } ?: false

        // Member properties shouldn't be mutable since validated form data must not be mutated
        val hasMutableProperties = formDataClass.memberProperties.any { it is KMutableProperty<*> }

        return hasPrimaryConstructor && hasDefaultConstructorParameters && !hasMutableProperties
    }

    private fun <V : Any?> syntaxRequirementSatisfied(field: KProperty1<T, V>): Boolean {
        val fieldValidationAnnotations = field.annotations.filter {
            it.annotationClass.hasAnnotation<FieldValidation<*>>()
        }
        return fieldValidationAnnotations.all {
            val validationAnnotation = it.annotationClass.findAnnotation<FieldValidation<*>>()
            val allowedTypeName = validationAnnotation?.fieldType?.jvmName
            val receivedTypeName = field.javaField?.type?.name
            allowedTypeName == receivedTypeName
        }
    }

    /**
     * A factory method that receives the property reference and constructs [FormField] object
     *
     * @param field kotlin property reference to the field
     * @return [FormField] instance ready to operate with
     */
    private fun initializeField(field: KProperty1<T, *>): FormField<Any> {
        // Fetches all the fields annotated with a valid [FieldValidation]
        val fieldAnnotations = field.annotations.filter {
            it.annotationClass.hasAnnotation<FieldValidation<*>>()
        }
        val validators = fieldAnnotations.map {
            val validationAnnotation = it.annotationClass.findAnnotation<FieldValidation<*>>()
            // Fetches the [ValidationRule] object from the annotation
            val validationRule = validationAnnotation?.validator?.objectInstance as ValidationRule<Any, Annotation>
            // FieldValidator with options
            FieldValidator(
                validationRule = validationRule,
                options = it
            )
        }.toSet()

        return FormFieldImpl(
            fieldClass = field as KProperty1<T, Any>,
            validators = validators
        )
    }

    /**
     * Builds the form data using it's primary constructor
     *
     * **REQUIREMENT**: We need the primary constructor to have default parameter values
     *
     * @return an instance of [T] with all the field values
     */
    private fun constructFormData(): T {
        val constructor = formClass.constructors.singleOrNull {
            it.parameters.all(KParameter::isOptional)
        }

        checkNotNull(constructor) { "A constructor with optional values is required." }

        val parameters = constructor.parameters
        val argumentMap = parameters
            .filter { param ->
                // Filter parameters with null field-value and not included in registered field
                fieldMap.values.singleOrNull { it.fieldName == param.name }?.value != null
            }
            .associateWith { param ->
                fieldMap.values.single { it.fieldName == param.name }.value
            }

        return constructor.callBy(argumentMap)
    }
}
