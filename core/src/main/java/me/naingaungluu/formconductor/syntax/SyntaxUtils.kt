package me.naingaungluu.formconductor.syntax

import kotlin.reflect.KClass
import kotlin.reflect.KProperty


/**
 * A Helpful util function to produce proper error message
 *
 * @param T form class type
 * @param V field type
 * @param formClass KClass reference of the form class
 * @param field KProperty reference of the field
 * @return Error message string
 */
fun <T : Any, V : Any?> SyntaxResult.Error.getErrorMessage(
    formClass: KClass<T>,
    field: KProperty<V>
): String {
    return when (this) {
        is SyntaxResult.Error.InvalidType -> {
            """
                ERROR WHILE CONSTRUCTING FORM: ${formClass.simpleName}.
                Field type constraints failed.
                Validation Annotation `$annotationName` for type $expectedType cannot be applied to field `${field.name}` of type $receivedType
            """.trimIndent()
        }
    }
}