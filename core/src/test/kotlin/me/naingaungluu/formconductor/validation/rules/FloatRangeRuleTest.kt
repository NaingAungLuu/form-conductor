package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.FloatRange
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FloatRangeRuleTest {

    @Test
    fun `rule return success for correct values`() {
        val options= FloatRange(min = 0.0, max = 1.0)

        FloatRangeRule.validate(0.0f, options) `should be equal to` FieldResult.Success

        FloatRangeRule.validate(0.1f, options) `should be equal to` FieldResult.Success

        FloatRangeRule.validate(1.0f, options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule return error for invalid values`() {
        val options= FloatRange(min = 0.0, max = 1.0)

        FloatRangeRule.validate(- 0.1f, options) `should be equal to` FieldResult.Error("Value out of range", FloatRangeRule)

        FloatRangeRule.validate(10f, options) `should be equal to` FieldResult.Error("Value out of range", FloatRangeRule)

        FloatRangeRule.validate(1.001f, options) `should be equal to` FieldResult.Error("Value out of range", FloatRangeRule)
    }
}