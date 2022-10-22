package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.validation.ValidationRule

object EmailAddressValidationRule : ValidationRule<String, EmailAddress> {

    const val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"

    override fun validate(value: String, options: EmailAddress): FieldResult {
        return if (value.matches(emailPattern.toRegex())) {
            FieldResult.Success
        } else if (value.isEmpty()) {
            FieldResult.NoInput
        } else {
            FieldResult.Error("Invalid Format", this)
        }
    }
}
