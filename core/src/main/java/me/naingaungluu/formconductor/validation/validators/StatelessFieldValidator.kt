package me.naingaungluu.formconductor.validation.validators

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.validation.StatelessValidationRule

/**
 * A wrapper class or proxy that passes the annotation object as options to the validation rule
 *
 * This is especially useful for form fields to be reused outside of the library,
 * since the call site doesn't have to know about the options.
 *
 * @param V Type of Field Value
 * @param A Type of the annotation
 *
 * @property validationRule rule object
 * @property options annotation object
 */
internal class StatelessFieldValidator<V : Any?, A : Annotation>(
    private val validationRule: StatelessValidationRule<V, A>,
    private val options: A
) : FieldValidator<V>() {
    /**
     * Validates the field using just it's value, since the option is known
     *
     * @param input value of type [V]
     * @return
     */
    override fun validate(input: V): FieldResult {
        return validationRule.validate(input, options)
    }
}