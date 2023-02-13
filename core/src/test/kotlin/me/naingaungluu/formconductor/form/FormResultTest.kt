package me.naingaungluu.formconductor.form

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.naingaungluu.formconductor.FormImpl
import me.naingaungluu.formconductor.FormResult
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FormResultTest {

    private lateinit var form: FormImpl<TestForm>

    @BeforeEach
    fun setUp() {
        form = FormImpl(TestForm::class)
    }

    @Test
    fun `submit returns correct result for no input`() {
        form.validate() `should be` FormResult.NoInput
    }

    @Test
    fun `submit returns success result for valid input`() = runTest {
        val expectedResult = FormResult.Success(TestForm(stringField = "Test", integerField = 10))
        form.setField(TestForm::stringField, "Test")
        form.setField(TestForm::integerField, 10)
        val result = form.validate()
        result `should be equal to` expectedResult
    }

    @Test
    fun `submit returns error result for invalid input`() = runTest {
        form.setField(TestForm::stringField, "Test")
        form.setField(TestForm::integerField, 9)
        val result = form.validate()
        result `should be instance of` FormResult.Error::class
    }
}
