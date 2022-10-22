package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.MaxLengthRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<String>(MaxLengthRule::class)
annotation class MaxLength(val value: Int)
