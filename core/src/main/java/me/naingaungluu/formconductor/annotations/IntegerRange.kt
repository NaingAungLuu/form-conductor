package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.IntegerRangeRule

// ktlint-disable filename

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Int>(IntegerRangeRule::class)
annotation class IntegerRange(
    val min: Int,
    val max: Int
)
