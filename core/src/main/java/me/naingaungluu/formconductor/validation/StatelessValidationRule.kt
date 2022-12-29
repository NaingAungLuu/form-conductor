package me.naingaungluu.formconductor.validation

import me.naingaungluu.formconductor.FieldResult

/**
 * Stateless Validation Rule interface
 *
 * You can implement this interface to create custom validation rules
 * that doesn't depend on form state
 *
 * @param T Type of value
 * @param A Type of associated annotation class
 */
interface StatelessValidationRule<T : Any?, A : Annotation>: ValidationRule {
    fun validate(value: T, options: A): FieldResult
}
