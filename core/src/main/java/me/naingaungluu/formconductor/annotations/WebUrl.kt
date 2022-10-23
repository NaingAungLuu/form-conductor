package me.naingaungluu.formconductor.annotations

//@FieldValidation
import me.naingaungluu.formconductor.validation.rules.WebUrlRule
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@FieldValidation<String>(WebUrlRule::class)
annotation class WebUrl(
    val httpRequired: Boolean = false
)
