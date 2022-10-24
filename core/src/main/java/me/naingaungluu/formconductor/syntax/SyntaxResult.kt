package me.naingaungluu.formconductor.syntax

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Result types for Syntax Processor
 *
 */
internal sealed class SyntaxResult {
    /**
     * Successful syntax
     */
    object Success : SyntaxResult()

    /**
     * Erroneous Syntax with failed constraints
     */
    sealed class Error : SyntaxResult() {
        /**
         * This error is thrown when an incompatible validator annotation is applied to a field
         *
         * @property expectedType allowed type by the annotation
         * @property receivedType actual type of the field
         * @property annotationName name of the annotation applied
         */
        data class InvalidType(
            val expectedType: String,
            val receivedType: String,
            val annotationName: String
        ) : Error()
    }
}
