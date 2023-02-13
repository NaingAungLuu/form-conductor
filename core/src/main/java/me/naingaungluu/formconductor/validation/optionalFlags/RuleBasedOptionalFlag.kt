package me.naingaungluu.formconductor.validation.optionalFlags

import me.naingaungluu.formconductor.CollectableFormData
import me.naingaungluu.formconductor.validation.rules.OptionalStateRule

internal class RuleBasedOptionalFlag<T: Any>(
    private val formState: CollectableFormData<T>,
    private val rule: OptionalStateRule<T>
) : OptionalFlag {
    override fun evaluate(): Boolean {
        return rule.isOptional(formState.collectFormData())
    }
}