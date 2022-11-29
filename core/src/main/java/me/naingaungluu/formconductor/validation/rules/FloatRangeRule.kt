package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.FloatRange
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for Ranged Float Values
 * The validation will only pass if the value is in specified bounds
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @see FloatRange
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object FloatRangeRule : ValidationRule<Float, FloatRange> {

    /**
     * Validates the field using the options passed to the [FloatRange] annotation
     *
     * @param value field value
     * @param options [FloatRange] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: Float, options: FloatRange): FieldResult<Any?> {
        val validationSuccess = (value >= options.min) && (value <= options.max)
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value out of range", this)
        }
    }
}
