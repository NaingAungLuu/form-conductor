package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.OptionalRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Any?>(OptionalRule::class)
annotation class Optional
