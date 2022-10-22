package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.IntegerRange
import me.naingaungluu.formconductor.validation.ValidationRule

internal object IntegerRangeValidationRule : ValidationRule<Int, IntegerRange> {
    override fun validate(value: Int, options: IntegerRange): FieldResult {
        val validationSuccess = (value >= options.min) && (value <= options.max)
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value out of range")
        }
    }
}
