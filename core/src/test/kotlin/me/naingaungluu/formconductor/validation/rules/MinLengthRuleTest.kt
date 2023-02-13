package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.annotations.MinLength
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MinLengthRuleTest {
    @Test
    fun `rule returns success for correct input`() {
        val options = MinLength(5)
        MinLengthRule.validate("123456", options) `should be equal to` FieldResult.Success
        MinLengthRule.validate("12345", options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule returns error for invalid input`() {
        val options = MinLength(5)
        val expectedResult = FieldResult.Error("Value shouldn't be shorter than ${options.value}.", MinLengthRule)

        MinLengthRule.validate("1234", options) `should be equal to` expectedResult
        MinLengthRule.validate("1", options) `should be equal to` expectedResult
    }
}