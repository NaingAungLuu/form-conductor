# 📜 Form Conductor
A declarative form validation library for Kotlin.

Form conductor is more than form validation. It provides a handful of reusable API to construct a form in simple easy steps.
Form conductor tries to tackle three aspects of forms:
- `Form Data Handling`
- `Form State Management`
- `Form Validation`

<br/>

![Maven Central](https://img.shields.io/maven-central/v/me.naingaungluu.formconductor/core?color=green&style=for-the-badge)
![Codecov](https://img.shields.io/codecov/c/github/NaingAungLuu/form-conductor?style=for-the-badge&token=KC7CK5DOZZ)
![GitHub issues](https://img.shields.io/github/issues/NaingAungLuu/form-conductor?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/NaingAungLuu/form-conductor?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/NaingAungLuu/form-conductor?style=for-the-badge)

## Table of contents

- [📜 Form Conductor](#-form-conductor)
  - [Table of contents](#table-of-contents)
  - [Documentation](#documentation)
  - [🔨 Form construction using built-in annotations](#-form-construction-using-built-in-annotations)
  - [Using Jetpack Compose](#using-jetpack-compose)
    - [Full Example](#full-example)
  - [Using Traditional Form Building (Android and JVM apps)](#using-traditional-form-building-android-and-jvm-apps)
    - [Declarative approach](#declarative-approach)
    - [Imperative Approach](#imperative-approach)
  - [Validation](#validation)
  - [Custom Validations](#custom-validations)
  - [Installation](#installation)
    - [Single dependency (imports all the modules as a single dependency)](#single-dependency-imports-all-the-modules-as-a-single-dependency)
    - [Modular dependency](#modular-dependency)
    - [Available Modules](#available-modules)

<br/>

## Documentation

Please check [the documentation webpage](https://naingaungluu.github.io/form-conductor) webpage o fully utitlize the power of `form-conductor` library.

<br/>

## 🔨 Form construction using built-in annotations

FormData.kt
```kotlin
@Form
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
<br><br>

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
<br>
<br>

## Using Traditional Form Building (Android and JVM apps)

<br>

`LoginForm.kt`

```kotlin
data class LoginForm(

    @EmailAddress
    val emailAddress: String = "",

    @MinLength(8)
    val password: String = ""
    
)
```

<br/>

### Declarative approach

`MainActivity.kt`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // Declarative Form Building
    val formState = form(LoginForm::class) {

        field(LoginForm::emailAddress) {

            etEmailAddress.doAfterTextChanged {
                this.setField(it)
            }

            this.resultStream.collectLatest {
                when(it) {
                    is FieldResult.Error -> {
                       /**
                        * Available properties in Error
                        * message - internal error message : String
                        * failedRule - ValidationRule<String, EmailAddress>
                        * 
                        * You can compose your error message as needed
                        */
                        etEmailAddress.error = it.message
                    }
                }
            }
        }
    }
}
```

<br/>

### Imperative Approach

`MainActivity.kt`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

     // Imperative Form Building
    val formState = form(LoginForm::class)
    val emailAddressState = form.field(LoginForm::emailAddress)
    val passwordState = form.field(LoginForm::password)

    etLogin.doAfterTextChanged {
        emailAddressState.setField(it)
    }
    etPassword.doAfterTextChanged {
        passwordState.setField(it)
    }

    emailAddresState.resultStream.collectLatest {
        if (it is FieldResult.Error) {
            etEmailAddress.error = it.message // or any error message as shown above
        }
    }
    
    formState.valueStream.collectLatest { result ->
        btnLogin.enabled = (result is FormResult.Success)
    }

    btnLogin.setOnClickListener {
        viewModel.login(formState.value)
    }
}
```

## Validation

Available Validation Annotations

```kotlin
// String
@EmailAddress

@Optional

@MaxLength(value)

@MinLength(value)

@WebUrl(httpRequired)


// Number
@FloatRange(min, max)

@IntegerRange(min, max)


// Boolean
@IsChecked

// More validations in development
```

<br/>

The great thing about `form-conductor` is it's very flexible. Each Validation annotation is decoupled from Validation rules.

If you don't like to use annotations, you can use from a list of built-in `ValidationRule` instead

```kotlin
// Each rule is associated to respective annotations

EmailAddressRule.validate(value)

FloatRangeRule.validate(value, FloatRange(min,max))

WebUrlRule.validate(value, WebUrl(httpRequired = true))
```

<br/><br/>

## Custom Validations

Feeling adventurous or feel like built-in validation rules aren't enough for you?

You can create your own validations rules and annotations to work with `form-conductor` instead. You can take advantage of `FieldValidation` annotation class and creat your custom annotations and validations.

```kotlin
// Custom Annotation

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@FieldValidation<LocalDate>(
    fieldType = LocalDate::class,
    validator = FutureDateRule::class
)
annotation class FutureDate


// Custom validation rule

object FutureDateRule : ValdiationRule<LocalDate, FutureDate> {
    override fun validate(value: LocalDate, options: FutureDate): FieldResult {
        // Your custom validation logic here
    }
}


// Usage
// This will automatically work with form-conductor

data class FormData(
    @FutureDate
    val date: LocalDate
)
```

## Installation

```groovy
// Groovy
dependencies {
    def formconductor_version = "0.4.0"
    implementation "me.naingaungluu.formconductor:core:$formconductor_version"
    implementation "me.naingaungluu.formconductor:compose-ui:$formconductor_version"
}

// Kts
dependencies {
    val formConductorVersion = "0.4.0"
    implementation("me.naingaungluu.formconductor:core:$formconductor_version")
    implementation("me.naingaungluu.formconductor:compose-ui:$formconductor_version")
}
```

#### Available Modules

`form-conductor:core` - Pure Kotlin library with all form validation features<br>
`form-conductor:compose-ui` - Android library with `form` and `field` composables with scopes for easy form state handling


