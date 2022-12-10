package me.naingaungluu.formconductor.builder

import me.naingaungluu.formconductor.Form
import me.naingaungluu.formconductor.FormField
import me.naingaungluu.formconductor.FormImpl
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

fun <T : Any> form(
    className: KClass<T>,
    builder: FormBuilderScope<T>.() -> Unit = {}
): Form<T> {
    val form = FormImpl(className)
    val scope = FormBuilderScopeImpl(form)
    scope.builder()
    return form
}

fun <T : Any, V : Any> FormBuilderScope<T>.field(
    fieldClass: KProperty1<T, V>,
    builder: FormFieldBuilderScope<V>.() -> Unit = {}
): FormField<V> {
    val field = registerField(fieldClass)
    val scope = FormFieldBuilderScopeImpl(field)
    scope.builder()
    return field
}

fun <T : Any, V : Any> Form<T>.field(
    fieldClass: KProperty1<T, V>,
    builder: FormFieldBuilderScope<V>.() -> Unit = {}
): FormField<V> {
    val field = registerField(fieldClass)
    val scope = FormFieldBuilderScopeImpl(field)
    scope.builder()
    return field
}
