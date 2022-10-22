package me.naingaungluu.formconductor.composeui.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FieldValue
import me.naingaungluu.formconductor.FormField

internal class FormFieldScopeImpl<V : Any>(
    private val formField: FormField<V>
) : FormFieldScope<V> {

    override val state: State<FieldValue<V>?>
        @Composable
        get() = formField.valueStream.collectAsState(null)

    override val resultState: State<FieldResult>
        @Composable
        get() = formField.resultStream.collectAsState(FieldResult.NoInput)

    override fun setField(input: V?) {
        formField.setField(input)
    }
}
