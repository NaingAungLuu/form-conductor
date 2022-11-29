package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.MutableStateFlow
import me.naingaungluu.formconductor.validation.FieldValidator
import me.naingaungluu.formconductor.validation.rules.OptionalRule
import kotlin.reflect.KProperty1

/**
 * Implementation of [FormField] Interface
 *
 * @param T Receiver type or the Form Data class.
 * @param V Type of the field
 * @property fieldClass kotlin property reference to the associated property of the field
 * @property validators a set of [FieldValidator] objects associated with the field
 * @property isOptional flags the field as optional
 */
internal class FormFieldImpl<T : Any, V : Any>(
    private val fieldClass: KProperty1<T, V>,
    private val validators: Set<FieldValidator<V, *>> = emptySet(),
    private val isOptional: Boolean = false
) : FormField<V> {

    override val fieldName: String = fieldClass.name

    override val valueStream: MutableStateFlow<FieldValue<V>> = MutableStateFlow(
        FieldValue(null)
    )

    override val resultStream: MutableStateFlow<FieldResult> = MutableStateFlow(FieldResult.NoInput)

    override val value: V?
        get() = valueStream.value.value

    /**
     * Sets the value of the field
     *
     * The function validates the field if the input is not **empty** or the field isn't **optional**
     *
     * @param input
     */
    override fun setField(input: V?) {
        valueStream.value = valueStream.value.copy(value = input)
        if (input != null) {
            resultStream.value = validate(input)
        } else {
            resultStream.value = if (isOptional) {
                FieldResult.Success
            } else {
                FieldResult.Error("This field is required", OptionalRule)
            }
        }
    }

    /**
     * Validates the input value without updating the field itself
     *
     * @param input input value of type [V]
     * @return [FieldResult] object after validation
     */
    override fun validate(input: V): FieldResult {
        // Validate using each of the validators
        val validationResult = validators.map { it.validate(input) }

        // Check if all validation is Success
        val validationSuccess = validationResult.all { it is FieldResult.Success }

        // Check if error exists
        val error = validationResult.firstOrNull { it is FieldResult.Error }

        return if (validationSuccess) {
            FieldResult.Success
        } else {
            // returns No Input when there's no error and validation fails
            error ?: FieldResult.NoInput
        }
    }
}
