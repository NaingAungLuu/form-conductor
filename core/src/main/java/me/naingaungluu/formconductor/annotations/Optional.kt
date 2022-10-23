package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.OptionalRule

/**
 * Property Annotation for [OptionalRule]
 *
 * Use this to indicate if a field is Optional
 *
 * Allowed only to annotate on properties of type [Any]
 *
 * @see OptionalRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation<Any?>(OptionalRule::class)
annotation class Optional
