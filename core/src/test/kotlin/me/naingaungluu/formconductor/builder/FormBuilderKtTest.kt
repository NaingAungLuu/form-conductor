package me.naingaungluu.formconductor.builder

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import me.naingaungluu.formconductor.*
import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.form.TestForm
import org.amshove.kluent.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FormBuilderKtTest {

    @Form
    data class MockForm(
        val arg1: String = ""
    )

    @Test
    fun `form builder function returns correct instance`() {
        val form = form(MockForm::class)
        form `should be instance of` FormImpl::class
        form `should be instance of` me.naingaungluu.formconductor.Form::class
    }

    @Test
    fun `form builder function returns correct builder scope`() = runTest {
        form(MockForm::class) {
            this `should be instance of` FormBuilderScopeImpl::class
            this `should be instance of` FormBuilderScope::class
            this.formState `should be instance of` Flow::class
        }
    }

    @Test
    fun `form builder scope functions properly`() = runTest {
        form(MockForm::class) {
            this.registerField(MockForm::arg1) `should be instance of` FormFieldImpl::class
            this.registerField(MockForm::arg1) `should be instance of` FormField::class
        }
    }

    @Test
    fun `field builder function returns correct result`() = runTest {
        form(MockForm::class) {
            val field = field(MockForm::arg1)
            field `should be instance of` FormField::class
            field.fieldName.trim() `should be equal to` MockForm::arg1.name.trim()
            field.isFieldOptional() `should be` false
            field.resultStream `should be instance of` MutableStateFlow::class
            field.valueStream `should be instance of` MutableStateFlow::class
            field.value should {
                this == "" || this == null
            }
        }
    }

    @Test
    fun `field builder scope functions properly`() = runTest {
        form(MockForm::class) {
            field(MockForm::arg1) {
                this.setField("Test")
                this.resultState.value `should be equal to` FieldResult.Success
                this.state.value.value `should be equal to` "Test"
            }
        }
    }

    @Test
    fun `field builder extension functions returns correct result`() = runTest {
        val form = form(MockForm::class) {}
        val field = form.field(MockForm::arg1)
        field `should be instance of` FormField::class
        field.fieldName.trim() `should be equal to` MockForm::arg1.name.trim()
        field.isFieldOptional() `should be` false
        field.resultStream `should be instance of` MutableStateFlow::class
        field.valueStream `should be instance of` MutableStateFlow::class
        field.value should {
            this == "" || this == null
        }
    }

    @Test
    fun `field builder extension scope functions properly`() = runTest {
        val form = form(MockForm::class) {}
        form.field(MockForm::arg1) {
            this.setField("Test")
            this.resultState.value `should be equal to` FieldResult.Success
            this.state.value.value `should be equal to` "Test"
        }
    }

}