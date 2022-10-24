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

/**
 * form Composable
 *
 * This composable setup the form declarative by taking it's class reference as a parameter.
 * It accepts a composable content with FormScope as it's context.
 * By using this FormScope context, you can register fields or call the [field] compose to setup field UIs
 *
 * This composable returns a [Form] object which you can reference later to register more fields
 *
 *
 * @param T type of form data class
 * @param className kotlin class reference of the form
 * @param content composable content
 * @return
 */
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

/**
 * Field Composable
 *
 * This composable automatically registers the field in it's parent scope and
 * accepts [KProperty1] reference as a parameter.
 * It also accepts a composable content with a [FormFieldScope] as it's context.
 * You can use this context to set the field value, observe it's state and validation results
 *
 * @param T type of the form class
 * @param V type of field value
 * @param fieldClass class reference of the form data
 * @param content composable content
 * @return
 */
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
