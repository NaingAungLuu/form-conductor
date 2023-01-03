package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.FloatRange
import me.naingaungluu.formconductor.annotations.IntegerRange

/**
 * Validation Rule for Ranged Integer Values
 * The validation will only pass if the value is in specified bounds
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @see IntRange
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object IntegerRangeRule : StatelessValidationRule<Int, IntegerRange> {

    /**
     * Validates the field using the options passed to the [FloatRange] annotation
     *
     * @param value field value
     * @param options [IntegerRange] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: Int, options: IntegerRange): FieldResult {
        val validationSuccess = (value >= options.min) && (value <= options.max)
        return if (validationSuccess) {
            FieldResult.Success
        } else {
            FieldResult.Error("Value out of range", this)
        }
    }
}
