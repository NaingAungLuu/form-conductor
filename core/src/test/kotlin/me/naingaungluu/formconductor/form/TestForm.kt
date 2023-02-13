package me.naingaungluu.formconductor.form

import me.naingaungluu.formconductor.annotations.IntegerRange

data class TestForm(
    val stringField: String = "",
    @IntegerRange(min = 10, max = 100)
    val integerField: Int = 0
)
