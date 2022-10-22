package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.EmailAddressRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<String>(EmailAddressRule::class)
annotation class EmailAddress()
