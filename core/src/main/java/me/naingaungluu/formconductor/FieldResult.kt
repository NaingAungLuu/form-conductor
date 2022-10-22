package me.naingaungluu.formconductor

import me.naingaungluu.formconductor.validation.ValidationRule

sealed class FieldResult {

    object Success : FieldResult()

    data class Error(
        val message: String,
        val failedRule: ValidationRule<*, *>
    ) : FieldResult()

    object NoInput : FieldResult()
}
