package me.naingaungluu.formconductor.validation.validators

import me.naingaungluu.formconductor.FieldResult

internal interface FieldValidator<V: Any?> {
    fun validate(input: V): FieldResult
}