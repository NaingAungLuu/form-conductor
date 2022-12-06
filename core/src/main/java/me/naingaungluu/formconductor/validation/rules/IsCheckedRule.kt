package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.IsChecked
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for Checkboxes
 * The validation will only pass if the field is checked or value is true
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @see IsChecked
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object IsCheckedRule : ValidationRule<Boolean, IsChecked> {

    /**
     * Validates the field if it's checked
     *
     * @param value field value
     * @param options [IsChecked] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: Boolean, options: IsChecked): FieldResult<Any?> {
        return if (value) {
            FieldResult.Success
        } else {
            FieldResult.Error("This field is required to be checked", this)
        }
    }
}
