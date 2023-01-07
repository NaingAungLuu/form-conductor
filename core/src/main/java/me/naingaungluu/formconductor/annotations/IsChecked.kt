package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.IsCheckedRule

/**
 * Property Annotation for [IsCheckedRule]
 *
 * Use this to validate if a specific checkbox is checked
 *
 * Allowed only to annotate on properties of type [Boolean]
 *
 * @property min
 * @property max
 *
 * @see IsCheckedRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation(
    fieldType = Boolean::class,
    validator = IsCheckedRule::class
)
annotation class IsChecked
