package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for Email Addresses
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @see EmailAddress
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object EmailAddressRule : ValidationRule<String, EmailAddress> {

    /**
     * Custom baked email pattern that allows name@domain.postfix
     */
    private const val emailPattern = "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"

    /**
     * Validates the field using the options passed to the [EmailAddress] annotation
     *
     * @param value field value
     * @param options [EmailAddress] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: String, options: EmailAddress): FieldResult<Any?> {
        return if (value.matches(emailPattern.toRegex())) {
            FieldResult.Success
        } else if (value.isEmpty()) {
            FieldResult.NoInput
        } else {
            FieldResult.Error("Invalid Format", this)
        }
    }
}
