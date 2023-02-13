package me.naingaungluu.formconductor.validation.rules

import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.annotations.WebUrl
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class WebUrlRuleTest {
    @Test
    fun `rule returns success result for correct url input`() {
        val  options = WebUrl()
        WebUrlRule.validate("https://google.com", options) `should be equal to` FieldResult.Success
        WebUrlRule.validate("www.google.com", options) `should be equal to` FieldResult.Success
        WebUrlRule.validate("google.com", options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule returns error for invalid url input`() {
        val  options = WebUrl()
        val expectedResult = FieldResult.Error("Invalid URL", WebUrlRule)
        WebUrlRule.validate("https://abc", options) `should be equal to` expectedResult
        WebUrlRule.validate("random", options) `should be equal to` expectedResult
    }

    @Test
    fun `rule returns success result for correct https url input`() {
        val  options = WebUrl(httpRequired = true)
        WebUrlRule.validate("https://google.com", options) `should be equal to` FieldResult.Success
        WebUrlRule.validate("http://google.com", options) `should be equal to` FieldResult.Success
    }

    @Test
    fun `rule returns error for invalid https url input`() {
        val  options = WebUrl(true)
        val expectedResult = FieldResult.Error("Invalid URL", WebUrlRule)
        WebUrlRule.validate("https://abc", options) `should be equal to` expectedResult
        WebUrlRule.validate("random", options) `should be equal to` expectedResult
        WebUrlRule.validate("google.com", options) `should be equal to` expectedResult
    }
}