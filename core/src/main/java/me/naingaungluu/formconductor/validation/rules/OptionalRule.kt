package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.Optional
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for Optional Fields
 * The validation will always be Success sine the field is optional
 *
 * You are not encouraged to use this rule manually since it doesn't validate at all
 *
 * @see Optional
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object OptionalRule : ValidationRule<Any?, Optional> {
    // Nothing much here
    override fun validate(value: Any?, options: Optional): FieldResult {
        return FieldResult.Success
    }
}
