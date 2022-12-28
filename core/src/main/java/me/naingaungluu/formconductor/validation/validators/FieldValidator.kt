package me.naingaungluu.formconductor.validation.validators

import me.naingaungluu.formconductor.FieldResult

internal abstract class FieldValidator<V: Any?> {
    abstract fun validate(input: V): FieldResult
}