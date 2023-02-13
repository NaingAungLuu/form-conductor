package me.naingaungluu.formconductor.validation.optionalFlags

import me.naingaungluu.formconductor.utils.isFieldOptional
import kotlin.reflect.KProperty1

class DefaultOptionalFlag(
    private val isOptional: Boolean
): OptionalFlag {
    override fun evaluate(): Boolean = isOptional
}