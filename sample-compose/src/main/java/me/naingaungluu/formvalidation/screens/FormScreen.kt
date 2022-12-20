package me.naingaungluu.formvalidation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import me.naingaungluu.formconductor.FormResult
import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.annotations.IntegerRange
import me.naingaungluu.formconductor.annotations.IsChecked
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.composeui.field
import me.naingaungluu.formconductor.composeui.form
import me.naingaungluu.formconductor.validation.rules.EmailAddressRule
import me.naingaungluu.formconductor.validation.rules.MaxLengthRule
import me.naingaungluu.formconductor.validation.rules.MinLengthRule

@Form
data class SignUpFormData(
    @MinLength(2)
    val name: String = "",

    @IntegerRange(min = 0, max = 99)
    val age: Int = 0,

    @MinLength(2)
    @MaxLength(10)
    @EmailAddress
    val emailAddress: String = "",

    val gender: Gender = Gender.Male,

    @IsChecked
    val isMarried: Boolean = false
)

sealed class Gender(val value: String) {
    object Male : Gender("MALE")
    object Female : Gender("FEMALE")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FormScreen() {
    // API Spec
    Column {
        form(SignUpFormData::class) {
            field(SignUpFormData::name) {
                TextField(
                    value = state.value?.value.orEmpty(),
                    onValueChange = { setField(it.ifEmpty { null }) },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    label = { Text("Name") },
                    isError = resultState.value is me.naingaungluu.formconductor.FieldResult.Error
                )
                AnimatedVisibility(
                    resultState.value is me.naingaungluu.formconductor.FieldResult.Error
                ) {
                    val result = resultState.value
                    if (result is me.naingaungluu.formconductor.FieldResult.Error) {
                        Text(
                            text = result.message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                }
            }
            field(SignUpFormData::emailAddress) {
                TextField(
                    value = state.value?.value.orEmpty(),
                    onValueChange = this::setField,
                    modifier = Modifier.padding(20.dp),
                    label = { Text("Email Address") }
                )
                AnimatedVisibility(
                    resultState.value is me.naingaungluu.formconductor.FieldResult.Error
                ) {
                    val result = resultState.value
                    if (result is me.naingaungluu.formconductor.FieldResult.Error) {
                        val text = when (result.failedRule) {
                            EmailAddressRule -> "Please enter a valid email address"
                            MinLengthRule -> "Must be a minimum of 2 words"
                            MaxLengthRule -> "Must be 10 words maximum"
                            else -> "Invalid input"
                        }
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                }
            }
            field(SignUpFormData::gender) {
                Row(
                    Modifier
                        .padding(20.dp)
                        .selectableGroup()) {
                    RadioButton(
                        selected = state.value?.value == Gender.Male,
                        onClick = { setField(Gender.Male) },
                        modifier = Modifier.semantics { contentDescription = "Male" }
                    )
                    Text("Male")
                    RadioButton(
                        selected = state.value?.value == Gender.Female,
                        onClick = { setField(Gender.Female) },
                        modifier = Modifier.semantics { contentDescription = "Male" }
                    )
                    Text("Female")
                }
            }
            field(SignUpFormData::isMarried) {
                val currentState = state.value?.value
                Row {
                   Checkbox(
                       checked = state.value?.value == true,
                       onCheckedChange = {
                           setField(currentState?.not())
                       }
                   )
                }
            }

            when (val form = formState.value) {
                is FormResult.Success -> SignUpFormDataLayout(form.data)
                else -> Text("Invalid Form Input")
            }
            LaunchedEffect(this) {
                delay(5000)
                this@form.submit(
                    SignUpFormData(
                        name = "Harry",
                        age = 20,
                        emailAddress = "naingaunglu01@gmail.com",
                        gender = Gender.Female
                    )
                )
            }
        }
    }
}

@Composable
fun SignUpFormDataLayout(form: SignUpFormData) {
    Column(Modifier.padding(20.dp)) {
        Text("Form Data Result", textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
        Text("name : ${form.name}")
        Text("age : ${form.age}")
        Text("gender : ${form.gender}")
        Text("emailAddress : ${form.emailAddress}")
    }
}
