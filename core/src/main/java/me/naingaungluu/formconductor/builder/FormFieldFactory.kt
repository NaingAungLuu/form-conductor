package me.naingaungluu.formconductor.builder

import me.naingaungluu.formconductor.CollectableFormData
import me.naingaungluu.formconductor.FormField
import me.naingaungluu.formconductor.FormFieldImpl
import me.naingaungluu.formconductor.annotations.DynamicOptional
import me.naingaungluu.formconductor.utils.*
import me.naingaungluu.formconductor.validation.optionalFlags.DefaultOptionalFlag
import me.naingaungluu.formconductor.validation.optionalFlags.OptionalFlag
import me.naingaungluu.formconductor.validation.validators.FieldValidator
import me.naingaungluu.formconductor.validation.optionalFlags.RuleBasedOptionalFlag
import kotlin.reflect.KProperty1
import kotlin.reflect.full.*

internal class FormFieldFactory<T: Any> (
    private val form: CollectableFormData<T>
) {

    /**
     * Factory instance for [FieldValidator] classes
     */
    private val fieldValidatorFactory: FormFieldValidatorFactory<T> by lazy {
        FormFieldValidatorFactory(form)
    }

    /**
     * A factory method that receives the property reference and constructs [FormField] object
     *
     * @param field kotlin property reference to the field
     * @return [FormField] instance ready to operate with
     */
    fun create(field: KProperty1<T, *>): FormField<Any> {

        // Create field validators
        val validators = field
            .validationAnnotations()
            .map(fieldValidatorFactory::create)
            .toSet()

        val optionalFlagEvaluators: Set<OptionalFlag> = if (field.hasAnnotation<DynamicOptional>()) {
            field.findAnnotations<DynamicOptional>()
                .map {
                    RuleBasedOptionalFlag(form, it.getEvaluatorInstance())
                }
                .toSet()

        } else {
            setOf(
                DefaultOptionalFlag(field.isFieldOptional())
            )
        }

        return FormFieldImpl(
            fieldClass = field as KProperty1<T, Any>,
            validators = validators,
            optionalStateEvaluators = optionalFlagEvaluators
        )
    }
}