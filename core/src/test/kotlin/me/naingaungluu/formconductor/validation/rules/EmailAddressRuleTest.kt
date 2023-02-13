package me.naingaungluu.formconductor.validation.rules

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.FormImpl
import me.naingaungluu.formconductor.FormResult
import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.form.TestForm
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class EmailAddressRuleTest {

    @Form
    data class MockForm(
        @EmailAddress
        val email: String = ""
    )

    private lateinit var form: FormImpl<MockForm>

    @BeforeEach
    fun setUp() {
        form = FormImpl(MockForm::class)
    }

    @Test
    fun `rule yields correct result for no input`() = runTest {
        form.validate() `should be equal to` FormResult.NoInput
    }

    @Test
    fun `rule yields correct success result for annotated validations`() = runTest {
        val formData = MockForm(email = "hello@example.com")
        form.submit(formData) `should be equal to` FormResult.Success(formData)
    }

    @Test
    fun `rule yields correct error result for annotated validations`() = runTest {
        val formData = MockForm(email = "hello")
        form.submit(formData) `should be equal to` FormResult.Error(setOf(EmailAddressRule))
    }

}