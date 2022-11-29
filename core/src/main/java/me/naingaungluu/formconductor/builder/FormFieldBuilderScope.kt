package me.naingaungluu.formconductor.builder

import kotlinx.coroutines.flow.StateFlow
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FieldValue

interface FormFieldBuilderScope<V : Any> {
    val state: StateFlow<FieldValue<V>>

    val resultState: StateFlow<FieldResult>

    fun setField(input: V?)
}
