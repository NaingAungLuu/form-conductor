package me.naingaungluu.formconductor.validation

import me.naingaungluu.formconductor.FieldResult

/**
 * Validation Rule interface
 *
 * You can implement this interface to create custom validation rules
 *
 * @param T Type of value
 * @param A Type of associated annotation class
 */
interface ValidationRule<T : Any?, A : Annotation> {
    fun validate(value: T, options: A): FieldResult
}
