package me.naingaungluu.formconductor.mocks.model

import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.IntegerRange

object MockedForms {
//    data class
    data class MissingOneDefaultArgument(
        val arg1: Any,
        val arg2: String = ""
    )
    data class MissingAllDefaultArguments(
        val arg1: String,
        val arg2: String
    )
    data class SingleMutableProperty(
        var arg1: String = "",
        var arg2: String = ""
    )
    data class MultipleMutableProperty(
        var arg1: String = "",
        var arg2: String = ""
    )
    data class AnnotationPropertyTypeMismatch(
        @IntegerRange(0, 1)
        val arg1: String = ""
    )
    data class AnnotationPropertyTypeMismatch2(
        val arg1: String = "",
        @EmailAddress
        val arg2: Int = 0,
        @IntegerRange(0, 1)
        val arg3: String = ""
    )
}
