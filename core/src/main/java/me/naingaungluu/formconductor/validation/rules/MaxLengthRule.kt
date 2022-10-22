package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.validation.ValidationRule

object MaxLengthRule : ValidationRule<String, MaxLength> {
    override fun validate(value: String, options: MaxLength): FieldResult {
        val validationSuccess = value.count() <= options.value
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value shouldn't be longer than ${options.value}.", this)
        }
    }
}
