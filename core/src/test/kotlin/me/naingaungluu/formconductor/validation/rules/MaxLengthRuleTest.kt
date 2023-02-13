package me.naingaungluu.formconductor.validation.rules

import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.MaxLength
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MaxLengthRuleTest {
    @Test
    fun `rule returns success for correct input`() {
        val options = MaxLength(5)
        MaxLengthRule.validate("12345", options) `should be equal to` FieldResult.Success
        MaxLengthRule.validate("1234", options) `should be equal to` FieldResult.Success
        MaxLengthRule.validate("1", options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule returns error for invalid input`() {
        val options = MaxLength(5)
        val expectedResult = FieldResult.Error("Value shouldn't be longer than ${options.value}.", MaxLengthRule)
        MaxLengthRule.validate("123456", options) `should be equal to` expectedResult
    }
}