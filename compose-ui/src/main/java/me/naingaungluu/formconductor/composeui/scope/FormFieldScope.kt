package me.naingaungluu.formconductor.composeui.scope

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FieldValue

/**
 * A scope interface for FormField objects
 *
 * @param V type of field value
 */
interface FormFieldScope<V : Any> {

    /**
     * Observable State instance of [FieldValue]
     */
    val state: State<FieldValue<V>?>
        @Composable get

    /**
     * Observable State instance of [FieldResult]
     *
     * You can use this for validation results
     */
    val resultState: State<FieldResult>
        @Composable get

    /**
     * A setter function for field value
     *
     * You're encouraged to set the field value only using this function
     *
     * @param input input value
     */
    fun setField(input: V?)
}
