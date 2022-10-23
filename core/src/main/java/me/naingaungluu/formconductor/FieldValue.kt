package me.naingaungluu.formconductor

/**
 * A wrapper class for field values
 *
 * WARNING! Might remove this later
 *
 * @param T Type of value
 * @property value optional value
 *
 * @warning might remove later
 */
data class FieldValue<T : Any?>(
    val value: T? = null
)
