package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.MaxLengthRule

/**
 * Property Annotation for [MaxLengthRule]
 *
 * Use this to validate if a string has certain max length
 *
 * Allowed only to annotate on properties of type [String]
 *
 * @property value
 *
 * @see MaxLengthRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation(
    fieldType = String::class,
    validator = MaxLengthRule::class
)
annotation class MaxLength(val value: Int)
