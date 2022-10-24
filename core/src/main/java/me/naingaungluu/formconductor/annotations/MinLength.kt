package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.MinLengthRule

/**
 * Property Annotation for [MinLengthRule]
 *
 * Use this to validate if a string has certain min length
 *
 * Allowed only to annotate on properties of type [String]
 *
 * @property value
 *
 * @see MinLengthRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation<String>(
    fieldType = String::class,
    validator = MinLengthRule::class
)
annotation class MinLength(val value: Int)
