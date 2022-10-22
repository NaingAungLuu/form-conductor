package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.MutableStateFlow

interface FormField<V : Any> {
    val value: V?
    val valueStream: MutableStateFlow<FieldValue<V>>
    val resultStream: MutableStateFlow<FieldResult>
    val fieldName: String
    fun setField(input: V?)
    fun validate(input: V): FieldResult
}
