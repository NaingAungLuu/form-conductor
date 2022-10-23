package me.naingaungluu.formconductor.composeui.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import me.naingaungluu.formconductor.FormResult
import kotlin.reflect.KProperty1

/**
 * A scope interface for Form objects
 *
 * @param T
 */
interface FormScope<T : Any> {

    /**
     * Registers the field and returns a singleton FormField instance
     *
     * @param V type of field value
     * @param fieldClass kotlin property reference of the field
     * @return
     */
    fun <V : Any> registerField(fieldClass: KProperty1<T, V>): me.naingaungluu.formconductor.FormField<V>

    val formState: State<FormResult<T>>
        @Composable get
}
