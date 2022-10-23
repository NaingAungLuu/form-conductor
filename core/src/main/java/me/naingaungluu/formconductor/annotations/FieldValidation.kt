package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.ValidationRule
import kotlin.reflect.KClass

/**
 * FieldValidation annotation is used to annotate on any field validator annotations
 *
 * You can use this to create your custom validation annotations
 *
 * Allowed only to annotate on Annotation classes
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class FieldValidation<T : Any?>(
    val validator: KClass<out ValidationRule<T, *>>
)
