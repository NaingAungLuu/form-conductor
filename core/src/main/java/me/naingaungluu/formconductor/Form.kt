package me.naingaungluu.formconductor

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KProperty1

interface Form<T : Any> {

    /**
     * Observable Flow of Form Data
     */
    val formDataStream: Flow<FormResult<T>>

    /**
     * Registers a field to the form and returns the [FormField] object to work with.
     *
     * You can call this method for the same field multiple times and each instance works the same
     *
     * @param V
     * @param fieldClass
     * @return a singleton [FormField<V>] object
     */
    fun <V : Any> registerField(fieldClass: KProperty1<T, V>): FormField<V>

    /**
     * Sets the value of a field through using form object
     *
     * @param V type of field value
     * @param fieldClass kotlin reflect reference to the property
     * @param value new field value
     */
    fun <V : Any> setField(fieldClass: KProperty1<T, V>, value: V)
}
