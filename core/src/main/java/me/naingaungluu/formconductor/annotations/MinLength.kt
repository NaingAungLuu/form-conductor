package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.MinLengthRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<String>(MinLengthRule::class)
annotation class MinLength(val value: Int)
