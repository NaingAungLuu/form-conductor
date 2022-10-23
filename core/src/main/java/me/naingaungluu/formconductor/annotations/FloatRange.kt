package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.FloatRangeRule

/**
 * Property Annotation for [FloatRangeRule]
 *
 * Use this to validate Float values to be in a specific range
 *
 * Allowed only to annotate on properties of type [Float]
 *
 * @property min minimum value of the integer
 * @property max maximum value of the integer
 *
 * @see FloatRangeRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation<Float>(FloatRangeRule::class)
annotation class FloatRange(
    val min: Double,
    val max: Double
)
