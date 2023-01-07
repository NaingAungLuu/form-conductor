package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult

interface StateBasedValidationRule<V : Any?, A : Annotation, T : Any> : ValidationRule {
    fun validate(value: V, options: A, formState: T): FieldResult
}