package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.IsChecked
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IsCheckedRuleTest {
    private val options = IsChecked()

    @Test
    fun `rule returns success for correct input`() {
        IsCheckedRule.validate(true, options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule returns error for invalid input`() {
        val expectedResult = FieldResult.Error("This field is required to be checked", IsCheckedRule)
        IsCheckedRule.validate(false, options) `should be equal to` expectedResult
    }
}