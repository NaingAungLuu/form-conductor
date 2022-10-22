package me.naingaungluu.formconductor.validation

import me.naingaungluu.formconductor.FieldResult

internal class FieldValidator<T : Any?, A : Annotation>(
    private val validationRule: ValidationRule<T, A>,
    private val options: A
) {
    fun validate(input: T): FieldResult {
        return validationRule.validate(input, options)
    }
}
