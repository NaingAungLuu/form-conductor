package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.FloatRangeRule
import me.naingaungluu.formconductor.validation.rules.IntegerRangeRule

/**
 * Property Annotation for [IntegerRangeRule]
 *
 * Use this to validate Integer values to be in a specific range
 *
 * Allowed only to annotate on properties of type [Int]
 *
 * @property min minimum value of the integer
 * @property max maximum value of the integer
 *
 * @see IntegerRangeRule
 * @author Naing Aung Luu
 * @since 0.0.1
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation(
    fieldType = Int::class,
    validator = IntegerRangeRule::class
)
annotation class IntegerRange(
    val min: Int,
    val max: Int
)
