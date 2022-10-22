package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.MutableStateFlow
import me.naingaungluu.formconductor.validation.FieldValidator
import me.naingaungluu.formconductor.validation.rules.OptionalRule
import kotlin.reflect.KProperty1

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

    /*
        Validations
     */

    override fun validate(input: V): FieldResult {
        val validationResult = validators.map { it.validate(input) }
        val validationSuccess = validationResult.all { it is FieldResult.Success }
        val error = validationResult.firstOrNull { it is FieldResult.Error }
        return if (validationSuccess) {
            FieldResult.Success
        } else error ?: FieldResult.NoInput
    }
}
