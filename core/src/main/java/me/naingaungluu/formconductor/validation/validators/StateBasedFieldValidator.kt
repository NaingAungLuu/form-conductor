package me.naingaungluu.formconductor.validation.validators

import me.naingaungluu.formconductor.CollectableFormData
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.validation.rules.StateBasedValidationRule

/**
 * This is a wrapper class for a [StateBasedValidationRule] instance.
 * This class makes the validation rules to be reused on the same form instance,
 * without having to re-specify the options.
 *
 * The class automatically provides the options and
 * current state of the form whenever a validation occurs
 *
 * @param form parent form as a [CollectableFormData]
 * @param validationRule [StateBasedValidationRule] instance to be wrapped
 * @param options validation parameters required by the rule
 */

internal class StateBasedFieldValidator<T: Any, V: Any?, A: Annotation>(
    private val form: CollectableFormData<T>,
    private val validationRule: StateBasedValidationRule<V, A, T>,
    private val options: A
) : FieldValidator<V> {
    override fun validate(input: V): FieldResult {
        return validationRule.validate(input, options, form.collectFormData())
    }
}
