package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.IsChecked
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for String Lengths
 * The validation will only pass if the string value doesn't exceed the max length
 *
 * The max length is **Inclusive**
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @see MaxLength
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object MaxLengthRule : ValidationRule<String, MaxLength> {

    /**
     * Validates the field using the options passed to [MaxLength] annotation
     *
     * @param value field value
     * @param options [MaxLength] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: String, options: MaxLength): FieldResult {
        val validationSuccess = value.count() <= options.value
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value shouldn't be longer than ${options.value}.", this)
        }
    }
}
