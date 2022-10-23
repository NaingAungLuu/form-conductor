# 📜 Form Conductor
A declarative form validation library for Kotlin.

Form conductor is more than form validation. It provides a handful of reusable API to construct a form in simple easy steps.
Form conductor tries to tackle three aspects of forms: 
- `Form Data Handling`
- `Form State Management` 
- `Form Validation`

<br/>

## 🔨 Form construction using built-in annotations

FormData.kt
```kotlin
data class SignUpForm(
    @MinLength(2)
    val name: String = "",

    @IntegerRange(min = 18, max = 99)
    val age: Int = 0,

    @EmailAddress
    val emailAddress: String = "",

    val gender: Gender = Gender.Male,
    
    @Optional
    @MaxLength(150)
    val address: String? = null

    @IsChecked
    val termsAndConditionAgreed: Boolean = false
    
    @MaxLength(200)
    val bio: String = ""
)
```

## Using Jetpack Compose

```kotlin
@Composable
fun FormScreen() {
    Column {
        form(SignUpForm::class) {
            field(SignUpFormData::name) {
                TextField(
                    value = state.value?.value.orEmpty(),
                    onValueChange = this::setField,
                    isError = resultState.value is FieldResult.Error
                )
            }
            field(SignUpFormData::emailAddress) {
                TextField(
                    value = state.value?.value.orEmpty(),
                    onValueChange = this::setField
                )
            }
            field(SignUpFormData::gender) {
                Row(Modifier.selectableGroup()) {
                    RadioButton(
                        selected = state.value?.value == Gender.Male,
                        onClick = { setField(Gender.Male) },
                        modifier = Modifier.semantics { contentDescription = "Male" }
                    )
                    RadioButton(
                        selected = state.value?.value == Gender.Female,
                        onClick = { setField(Gender.Female) },
                        modifier = Modifier.semantics { contentDescription = "Male" }
                    )
                }
            }
        }
    }
}
```

#### Available Modules

`form-conductor:core`<br>`form-conductor:compose-ui`


