package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.validation.ValidationRule

object MinLengthRule : ValidationRule<String, MinLength> {
    override fun validate(value: String, options: MinLength): FieldResult {
        val validationSuccess = value.count() >= options.value
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value shouldn't be shorter than ${options.value}.", this)
        }
    }
}
