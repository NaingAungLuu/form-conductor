package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.EmailAddressRule

/**
 * Property Annotation for [EmailAddressRule]
 *
 * Allowed only to annotate on properties of type [String]
 * @see EmailAddressRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation<String>(
    fieldType = String::class,
    validator = EmailAddressRule::class
)
annotation class EmailAddress()
