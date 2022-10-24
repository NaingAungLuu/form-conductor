package me.naingaungluu.formconductor.builder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import me.naingaungluu.formconductor.FormField
import me.naingaungluu.formconductor.FormResult
import kotlin.reflect.KProperty1

interface FormBuilderScope<T : Any> {

    val formState: Flow<FormResult<T>>

    fun <V : Any> registerField(fieldClass: KProperty1<T, V>): FormField<V>
}
