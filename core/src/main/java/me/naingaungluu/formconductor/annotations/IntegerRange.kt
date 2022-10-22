package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.IntegerRangeValidationRule

// ktlint-disable filename

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Int>(IntegerRangeValidationRule::class)
annotation class IntegerRange(
    val min: Int,
    val max: Int
)
