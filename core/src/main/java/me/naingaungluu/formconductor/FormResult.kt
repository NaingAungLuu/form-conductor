package me.naingaungluu.formconductor

import me.naingaungluu.formconductor.validation.ValidationRule

sealed class FormResult<out T : Any> {

    data class Success<T : Any> (
        val data: T
    ) : FormResult<T>()

    data class Error(
        val failedRules: Set<ValidationRule<*, *>>
    ) : FormResult<Nothing>()

    object NoInput : FormResult<Nothing>()
}
