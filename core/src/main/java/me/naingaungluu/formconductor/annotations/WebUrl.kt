package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.OptionalRule
import me.naingaungluu.formconductor.validation.rules.WebUrlRule

/**
 * Property Annotation for [WebUrlRule]
 *
 * Use this to validate if a string is a URL
 *
 * Allowed only to annotate on properties of type [String]
 *
 * @param httpRequired true if the url must start with "http" or "https"
 *
 * @see OptionalRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation(
    fieldType = String::class,
    validator = WebUrlRule::class
)
annotation class WebUrl(
    val httpRequired: Boolean = false
)
