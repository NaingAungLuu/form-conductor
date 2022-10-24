package me.naingaungluu.formconductor.builder

import kotlinx.coroutines.flow.Flow
import me.naingaungluu.formconductor.FormField
import me.naingaungluu.formconductor.FormImpl
import me.naingaungluu.formconductor.FormResult
import kotlin.reflect.KProperty1

class FormBuilderScopeImpl<T : Any>(
    private val form: FormImpl<T>
) : FormBuilderScope<T> {

    override val formState: Flow<FormResult<T>>
        get() = form.formDataStream

    override fun <V : Any> registerField(fieldClass: KProperty1<T, V>): FormField<V> {
        return form.registerField(fieldClass)
    }
}
