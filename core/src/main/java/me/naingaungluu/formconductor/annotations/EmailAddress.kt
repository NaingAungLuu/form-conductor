package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.EmailAddressValidationRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<String>(EmailAddressValidationRule::class)
annotation class EmailAddress()
