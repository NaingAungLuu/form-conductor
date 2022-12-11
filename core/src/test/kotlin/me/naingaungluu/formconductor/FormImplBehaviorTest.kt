package me.naingaungluu.formconductor

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import me.naingaungluu.formconductor.mocks.model.MockedForms
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

/**
 * Test Form Implementation class
 *
 * We're using [TestForm] class to mock a form
 */
@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FormImplBehaviorTest {

    private lateinit var form: FormImpl<TestForm>

    @BeforeEach
    fun setUp() {
        form = FormImpl(TestForm::class)
    }

    /**
     * Form Data Stream
     *
     */
    @Test
    fun `form data stream emits a non-null value as first item`() = runTest {
        form.formDataStream.firstOrNull() `should not be` null
    }

    @Test
    fun `form data stream emits NoInput as first item`() = runTest {
        val firstEmittedItem = form.formDataStream.firstOrNull()
        firstEmittedItem `should be` FormResult.NoInput
    }

    /**
     * Form Construction
     */

    // Form constructors
    @Test
    fun `form construction should fail on a single missing default argument`() {
        assertThrows<IllegalArgumentException> {
            FormImpl(MockedForms.MissingOneDefaultArgument::class)
        }
    }

    @Test
    fun `form construction should fail on multiple missing default arguments`() {
        assertThrows<IllegalArgumentException> {
            FormImpl(MockedForms.MissingAllDefaultArguments::class)
        }
    }

    // Form properties
    @Test
    fun `form construction should fail on a single mutable property`() {
        assertThrows<IllegalArgumentException> {
            FormImpl(MockedForms.SingleMutableProperty::class)
        }
    }

    @Test
    fun `form construction should fail on a multiple mutable properties`() {
        assertThrows<IllegalArgumentException> {
            FormImpl(MockedForms.MultipleMutableProperty::class)
        }
    }

    // Type checks
    @Test
    fun `form construction should fail on field type and annotation mismatch`() {
        assertThrows<IllegalArgumentException> {
            FormImpl(MockedForms.AnnotationPropertyTypeMismatch::class)
        }
    }

    @Test
    fun `form construction should fail on field type and annotation multiple mismatches`() {
        assertThrows<IllegalArgumentException> {
            FormImpl(MockedForms.AnnotationPropertyTypeMismatch2::class)
        }
    }

    /**
     * Form Functionalities
     */
}
