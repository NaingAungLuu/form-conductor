package me.naingaungluu.formconductor.composeui.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FieldValue

interface FormFieldScope<V : Any> {

    val state: State<FieldValue<V>?>
        @Composable get

    val resultState: State<FieldResult>
        @Composable get

    fun setField(input: V?)
}
