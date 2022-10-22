package me.naingaungluu.formconductor.composeui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import me.naingaungluu.formconductor.Form
import me.naingaungluu.formconductor.FormField
import me.naingaungluu.formconductor.FormImpl
import me.naingaungluu.formconductor.composeui.scope.FormFieldScope
import me.naingaungluu.formconductor.composeui.scope.FormFieldScopeImpl
import me.naingaungluu.formconductor.composeui.scope.FormScope
import me.naingaungluu.formconductor.composeui.scope.FormScopeImpl
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

@Composable
fun <T : Any> form(
    className: KClass<T>,
    content: @Composable FormScope<T>.() -> Unit
): Form<T> {
    val form = remember { FormImpl(className) }
    val scope = remember {
        derivedStateOf { FormScopeImpl(form) }
    }
    scope.value.content()
    return form
}

@Composable
fun <T : Any, V : Any> FormScope<T>.field(
    fieldClass: KProperty1<T, V>,
    content: @Composable FormFieldScope<V>.() -> Unit
): FormField<V> {
    val field = registerField(fieldClass)
    val fieldScope = FormFieldScopeImpl(field)
    fieldScope.content()
    return field
}
