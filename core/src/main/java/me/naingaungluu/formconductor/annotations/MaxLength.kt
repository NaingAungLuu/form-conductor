package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.MaxLengthValidationRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<String>(MaxLengthValidationRule::class)
annotation class MaxLength(val value: Int)
