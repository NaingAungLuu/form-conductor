package me.naingaungluu.formconductor.builder

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FieldValue
import me.naingaungluu.formconductor.FormField

class FormFieldBuilderScopeImpl<V : Any>(
    private val formField: FormField<V>
) : FormFieldBuilderScope<V> {

    override val state: StateFlow<FieldValue<V>>
        get() = formField.valueStream.asStateFlow()

    override val resultState: StateFlow<FieldResult>
        get() = formField.resultStream.asStateFlow()

    override fun setField(input: V?) {
        formField.setField(input)
    }
}
