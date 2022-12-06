package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.annotations.WebUrl
import me.naingaungluu.formconductor.validation.ValidationRule

/**
 * Validation Rule for String Lengths
 * The validation will only pass if the string value isn't shorter than the min length
 *
 * You can use this validation rule to manually validate fields as well
 *
 * @param httpRequired
 * @see MinLength
 * @author Naing Aung Luu
 * @since 0.0.1
 */
object WebUrlRule : ValidationRule<String, WebUrl> {

    /**
     * Custom baked url pattern without a scheme prefix, "https" or "http"
     */
    private const val DOMAIN_URL_PATTERN = "^[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b[-a-zA-Z0-9()@:%_+.~#?&/=]*\$"

    /**
     * Custom baked url pattern with a scheme prefix, "https" or "http"
     */
    private const val HTTP_URL_PATTERN = "^https?://(?:www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b[-a-zA-Z0-9()@:%_+.~#?&/=]*\$"

    /**
     * Validates the field using the options passed to [MaxLength] annotation
     *
     * @param value field value
     * @param options [MinLength] object annotated to the field
     * @return [FieldResult]
     */
    override fun validate(value: String, options: WebUrl): FieldResult<Any?> {
        val pattern = if (options.httpRequired) {
            HTTP_URL_PATTERN.toRegex()
        } else {
            DOMAIN_URL_PATTERN.toRegex()
        }
        return if (value.matches(pattern)) {
            FieldResult.Success
        } else {
            FieldResult.Error("Invalid URL", this)
        }
    }
}
