package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.Optional
import me.naingaungluu.formconductor.validation.ValidationRule

object OptionalValidationRule : ValidationRule<Any?, Optional> {
    override fun validate(value: Any?, options: Optional): FieldResult {
        return FieldResult.Success
    }
}
