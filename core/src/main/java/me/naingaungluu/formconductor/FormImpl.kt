package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import me.naingaungluu.formconductor.annotations.FieldValidation
import me.naingaungluu.formconductor.validation.FieldValidator
import me.naingaungluu.formconductor.validation.ValidationRule
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

class FormImpl<T : Any>(
    private val formClass: KClass<T>
) : Form<T> {

    private val fieldMap: Map<KProperty1<T, *>, FormField<Any>>
    override val formDataStream: Flow<FormResult<T>>

    init {

        val fields = formClass.memberProperties.toSet()

        fieldMap = fields.associateWith { initializeField(it) }

        formDataStream = combine(fieldMap.values.map { it.valueStream.asSharedFlow() }) { _ ->

            val formSuccess = fieldMap.values
                .map { it.resultStream.value }
                .all { it is FieldResult.Success || it is FieldResult.NoInput }

            if (formSuccess) {
                val formData = constructFormData()
                FormResult.Success(formData)
            } else {
                FormResult.Error
            }
        }.catch {
            when (it) {
                is java.lang.IllegalStateException -> emit(FormResult.Error)
            }
        }
    }

    override fun <V : Any> registerField(fieldClass: KProperty1<T, V>): FormField<V> {
        return getField(fieldClass)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <V : Any> getField(fieldClass: KProperty1<T, V>): FormField<V> {
        return fieldMap[fieldClass]!! as FormField<V>
    }

    override fun <V : Any> setField(
        fieldClass: KProperty1<T, V>,
        value: V
    ) {
        val field = getField(fieldClass)
        field.setField(value)
    }

    private fun initializeField(field: KProperty1<T, *>): FormField<Any> {
        val optionalAnnotation = field.annotations.filter { it.annotationClass.hasAnnotation<FieldValidation<*>>() }
        val validators = optionalAnnotation.map {
            val validationAnnotation = it.annotationClass.findAnnotation<FieldValidation<*>>()
            val validationRule = validationAnnotation?.validator?.objectInstance as ValidationRule<Any, Annotation>
            FieldValidator(validationRule, it)
        }.toSet()
        return FormFieldImpl(
            fieldClass = field as KProperty1<T, Any>,
            validators = validators
        )
    }

    private fun constructFormData(): T {
        val constructor = formClass.constructors.singleOrNull {
            it.parameters.all(KParameter::isOptional)
        }

        checkNotNull(constructor)

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
