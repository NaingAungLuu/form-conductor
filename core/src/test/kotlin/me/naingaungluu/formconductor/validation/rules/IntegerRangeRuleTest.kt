package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.IntegerRange
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IntegerRangeRuleTest {
    @Test
    fun `rule return success for correct values`() {
        val options= IntegerRange(min = 0, max = 10)

        IntegerRangeRule.validate(0, options) `should be equal to` FieldResult.Success

        IntegerRangeRule.validate(5, options) `should be equal to` FieldResult.Success

        IntegerRangeRule.validate(10, options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule return error for invalid values`() {
        val options= IntegerRange(min = 0, max = 10)

        IntegerRangeRule.validate(-1, options) `should be equal to` FieldResult.Error("Value out of range", IntegerRangeRule)

        IntegerRangeRule.validate(11, options) `should be equal to` FieldResult.Error("Value out of range", IntegerRangeRule)
    }
}