package me.naingaungluu.formconductor.validation

import me.naingaungluu.formconductor.FieldResult

/**
 * A wrapper class or proxy that passes the annotation object as options to the validation rule
 *
 * This is especially useful for [FormField] instances to be reused outside of the library,
 * since the call site doesn't have to know about the options.
 *
 * @param T Type of Field Value
 * @param A Type of the annotation
 *
 * @property validationRule rule object
 * @property options annotation object
 */
internal class FieldValidator<T : Any?, A : Annotation>(
    private val validationRule: ValidationRule<T, A>,
    private val options: A
) {
    /**
     * Validates the field using just it's value, since the option is known
     *
     * @param input value of type [T]
     * @return
     */
    fun validate(input: T): FieldResult {
        return validationRule.validate(input, options)
    }
}
