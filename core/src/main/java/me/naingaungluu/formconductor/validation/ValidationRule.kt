package me.naingaungluu.formconductor.validation

import me.naingaungluu.formconductor.FieldResult

interface ValidationRule<T : Any?, A : Annotation> {
    fun validate(value: T, options: A): FieldResult
}
