package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.IsChecked
import me.naingaungluu.formconductor.validation.ValidationRule

object IsCheckedRule : ValidationRule<Boolean, IsChecked> {
    override fun validate(value: Boolean, options: IsChecked): FieldResult {
        return if (value) {
            FieldResult.Success
        } else {
            FieldResult.Error("This field is required to be checked", this)
        }
    }
}
