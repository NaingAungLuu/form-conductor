package me.naingaungluu.formconductor.syntax

import kotlin.reflect.KProperty1

internal interface SyntaxProcessor {
    fun <T : Any, V : Any?> process(field: KProperty1<T, V>): SyntaxResult
}
