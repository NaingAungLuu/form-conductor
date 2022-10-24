# 📜 Form Conductor
A declarative form validation library for Kotlin.

Form conductor is more than form validation. It provides a handful of reusable API to construct a form in simple easy steps.
Form conductor tries to tackle three aspects of forms: 
- `Form Data Handling`
- `Form State Management` 
- `Form Validation`

<br/>

![JitPack](https://img.shields.io/jitpack/version/com.github.NaingAungLuu/form-conductor?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/NaingAungLuu/form-conductor?style=for-the-badge)
![GitHub issues](https://img.shields.io/github/issues/NaingAungLuu/form-conductor?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/NaingAungLuu/form-conductor?style=for-the-badge)

## Table of contents

- [Form construction using built-in annotations](#-form-construction-using-built-in-annotations)
- [Using Jetpack Compose](#using-jetpack-compose)
- [Installation](#installation)

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

`form` composable

```kotlin
@Composable
fun FormScreen() {
    Column {
        form(SignUpForm::class) {
           /**
            * Following properties are available
            * formState - State<FormResult<SignUpForm>>
            * registerField() - returns field object
            */
            Button(
                text = "Sign Up",
                enabled = this.formState.value is FormResult.Success
            )
        }
    }
}
```

`field` composable

```kotlin
form(SignUpForm::class) {
    field(SignUpForm::name) {
       /**
        * Following properties are available
        * state - compose state with field value: State<FieldValue<String>>
        * resultState - validation result state: State<FieldResult<String>>
        * setField() - sets the field value and validate
        */
        TextField(
            value = state.value?.value.orEmpty(),
            onValueChange = this::setField,
            isError = resultState.value is FieldResult.Error
        )
    }
}
```

#### Full Example
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

## Installation

#### Single dependency (imports all the modules as a single dependency)
```groovy
// Groovy
dependencies {
    implementation "com.github.NaingAungLuu:form-conductor:$<latest_version>"
}

// Kts
dependencies {
    implementation("com.github.NaingAungLuu:form-conductor:$<latest_version>")
}
```


#### Modular dependency
```groovy
// Groovy
dependencies {
    implementation "com.github.NaingAungLuu.form-conductor:core:$<latest_version>"
    implementation "com.github.NaingAungLuu.form-conductor:compose-ui:$<latest_version>"
}

// Kts
dependencies {
    implementation("com.github.NaingAungLuu.form-conductor:core:$<latest_version>")
    implementation("com.github.NaingAungLuu.form-conductor:compose-ui:$<latest_version>")
}
```

#### Available Modules

`form-conductor:core` - Pure Kotlin library with all form validation features<br>
`form-conductor:compose-ui` - Android library with `form` and `field` composables with scopes for easy form state handling


