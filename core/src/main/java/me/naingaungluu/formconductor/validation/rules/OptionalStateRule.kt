package me.naingaungluu.formconductor.validation.rules

interface OptionalStateRule<T> {
    fun isOptional(state: T): Boolean
}