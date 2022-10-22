package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.ValidationRule
import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldValidation<T : Any?>(
    val validator: KClass<out ValidationRule<T, *>>
)
