package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.IsCheckedValidationRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Boolean>(IsCheckedValidationRule::class)
annotation class IsChecked
