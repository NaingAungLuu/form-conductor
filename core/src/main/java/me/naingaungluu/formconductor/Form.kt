package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KProperty1

interface Form<T : Any> {

    val formDataStream: Flow<FormResult<T>>

    fun <V : Any> registerField(fieldClass: KProperty1<T, V>): FormField<V>

    fun <V : Any> setField(fieldClass: KProperty1<T, V>, value: V)
}
