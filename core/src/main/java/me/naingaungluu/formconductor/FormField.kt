package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.MutableStateFlow

interface FormField<V : Any> {

    /**
     * Holds the current value of the field
     */
    val value: V?

    /**
     * A mutable state flow of unvalidated field value
     *
     * Use this stream if you wanna perform validations yourself
     */
    val valueStream: MutableStateFlow<FieldValue<V>>

    /**
     * A mutable state flow of validated field results
     *
     * Use this stream to get validation results
     */
    val resultStream: MutableStateFlow<FieldResult>

    /**
     * Field name property
     */
    val fieldName: String

    /**
     * Optional Flag
     */
    val isOptional: Boolean

    /**
     * Sets the value of the field
     *
     * The function validates the field if the input is not **empty** or the field isn't **optional**
     *
     * @param input
     */
    fun setField(input: V?)

    /**
     * Validate the field without updating it's value
     *
     * @param input An input value of the same type as the filed
     * @return [FieldResult] object after validation
     */
    fun validate(input: V): FieldResult
}
