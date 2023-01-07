package me.naingaungluu.formconductor.builder

import me.naingaungluu.formconductor.CollectableFormData
import me.naingaungluu.formconductor.annotations.FieldValidation
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.utils.getFieldValidationData
import me.naingaungluu.formconductor.validation.rules.StateBasedValidationRule
import me.naingaungluu.formconductor.validation.rules.StatelessValidationRule
import me.naingaungluu.formconductor.validation.validators.FieldValidator
import me.naingaungluu.formconductor.validation.validators.StateBasedFieldValidator
import me.naingaungluu.formconductor.validation.validators.StatelessFieldValidator

internal class FormFieldValidatorFactory<T: Any>(
    private val form: CollectableFormData<T>
) {
    fun create(
        validatorOptions: Annotation
    ): FieldValidator<Any> {
        val fieldValidationAnnotation: FieldValidation = validatorOptions.getFieldValidationData()
        return when (
            val validationRule = fieldValidationAnnotation.validator.objectInstance
        ) {
            is StatelessValidationRule<*, *> ->
                StatelessFieldValidator(
                    validationRule = validationRule as StatelessValidationRule<Any, Annotation>,
                    options = validatorOptions
                )
            is StateBasedValidationRule<*, *, *> ->
                StateBasedFieldValidator(
                    validationRule = validationRule as StateBasedValidationRule<Any, Annotation, T>,
                    options = validatorOptions,
                    form = form
                )
            else -> {
                throw IllegalArgumentException(
                    """
                        |Incompatible Validation Rule type ${fieldValidationAnnotation.validator.simpleName} is provided
                        |for annotation ${validatorOptions.annotationClass.simpleName}
                    """.trimMargin()
                )
            }
        }
    }
}