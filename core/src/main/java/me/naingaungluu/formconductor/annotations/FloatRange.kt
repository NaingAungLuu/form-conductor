package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.FloatRangeRule
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Float>(FloatRangeRule::class)
annotation class FloatRange(
    val min: Double,
    val max: Double
)
