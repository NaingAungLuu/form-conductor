package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.MinLengthValidationRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<String>(MinLengthValidationRule::class)
annotation class MinLength(val value: Int)
