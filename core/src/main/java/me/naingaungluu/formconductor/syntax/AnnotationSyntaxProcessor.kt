package me.naingaungluu.formconductor.syntax

import me.naingaungluu.formconductor.annotations.FieldValidation
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.jvmName

/**
 * A syntax processor to check type compatibility with fields
 *
 */
class AnnotationSyntaxProcessor : SyntaxProcessor {
    override fun <T : Any, V : Any?> process(field: KProperty1<T, V>): SyntaxResult {
        val fieldValidationAnnotations = field.annotations.filter {
            it.annotationClass.hasAnnotation<FieldValidation<*>>()
        }
        val result = fieldValidationAnnotations.map {
            val validationAnnotation = it.annotationClass.findAnnotation<FieldValidation<*>>()
            val allowedTypeName = validationAnnotation?.fieldType?.jvmName
            val receivedTypeName = field.javaField?.type?.name
            if (allowedTypeName == receivedTypeName) {
                SyntaxResult.Success
            } else {
                SyntaxResult.Error.InvalidType(
                    receivedType = receivedTypeName.toString(),
                    expectedType = allowedTypeName.toString(),
                    annotationName = it.annotationClass.simpleName.toString()
                )
            }
        }
        val syntaxSatisfied = result.all { it is SyntaxResult.Success }
        return if (syntaxSatisfied) {
            SyntaxResult.Success
        } else {
            result.first { it is SyntaxResult.Error }
        }
    }
}
