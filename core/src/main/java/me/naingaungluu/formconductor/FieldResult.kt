package me.naingaungluu.formconductor

sealed class FieldResult {

    object Success : FieldResult()

    data class Error(val message: String) : FieldResult()

    object NoInput : FieldResult()
}
