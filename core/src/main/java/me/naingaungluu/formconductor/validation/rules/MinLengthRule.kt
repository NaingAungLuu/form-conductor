package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for String Lengths
 * The validation will only pass if the string value isn't shorter than the min length
 *
 * The min length is **Inclusive**
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @see MinLength
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object MinLengthRule : ValidationRule<String, MinLength> {

    /**
     * Validates the field using the options passed to [MaxLength] annotation
     *
     * @param value field value
     * @param options [MinLength] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: String, options: MinLength): FieldResult {
        val validationSuccess = value.count() >= options.value
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value shouldn't be shorter than ${options.value}.", this)
        }
    }
}
