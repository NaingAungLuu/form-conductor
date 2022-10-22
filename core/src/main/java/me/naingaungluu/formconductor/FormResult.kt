package me.naingaungluu.formconductor

sealed class FormResult<out T : Any> {
    data class Success<T : Any> (
        val data: T
    ) : FormResult<T>()
    object Error : FormResult<Nothing>()
    object NoInput : FormResult<Nothing>()
}
