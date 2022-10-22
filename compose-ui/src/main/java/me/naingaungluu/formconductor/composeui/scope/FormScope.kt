package me.naingaungluu.formconductor.composeui.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import me.naingaungluu.formconductor.FormResult
import kotlin.reflect.KProperty1

interface FormScope<T : Any> {
    fun <V : Any> registerField(fieldClass: KProperty1<T, V>): me.naingaungluu.formconductor.FormField<V>

    val formState: State<FormResult<T>>
        @Composable get
}
