package me.naingaungluu.formconductor.composeui.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import me.naingaungluu.formconductor.FormField
import me.naingaungluu.formconductor.FormImpl
import me.naingaungluu.formconductor.FormResult
import kotlin.reflect.KProperty1

internal class FormScopeImpl<T : Any>(
    private val form: FormImpl<T>
) : FormScope<T> {
    override fun <V : Any> registerField(
        fieldClass: KProperty1<T, V>
    ): FormField<V> {
        return form.registerField(fieldClass)
    }

    override val formState: State<FormResult<T>>
        @Composable
        get() = form.formDataStream.collectAsState(FormResult.NoInput)
}
