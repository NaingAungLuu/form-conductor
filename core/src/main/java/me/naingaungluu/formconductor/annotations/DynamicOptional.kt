package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.OptionalStateRule
import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class DynamicOptional(
    val evaluator: KClass<out OptionalStateRule<*>>
)
