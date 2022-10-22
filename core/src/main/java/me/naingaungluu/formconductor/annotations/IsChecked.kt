package me.naingaungluu.formconductor.annotations

import me.naingaungluu.formconductor.validation.rules.IsCheckedRule

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<Boolean>(IsCheckedRule::class)
annotation class IsChecked
