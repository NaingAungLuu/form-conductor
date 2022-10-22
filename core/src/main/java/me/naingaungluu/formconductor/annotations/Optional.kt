package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.OptionalValidationRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Any?>(OptionalValidationRule::class)
annotation class Optional
