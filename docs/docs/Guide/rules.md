---
title: Rules
sidebar_position: 2
---

In formconductor library, validation rules can be applied using kotlin `@Annotations`. Rules can only be applied to specified type of properties and can be stacked to apply multiple rules.

## Supported Rule Annotations

Fields can be annotated with built-in validators to apply a validation rule to it.

:::info
Every field without a specific `@Optional` annotation is marked as `Required/Mandatory` by default.
:::

|               Annotation              |               Rule            |        Type        |          Description         |
| :------------------------------------ | :---------------------------: | -----------------: |----------------------------: |
| @EmailAddress                        |  EmailAddressRule              | `String`           | Validates email address using the built-in regex pattern |
| @MinLength(value)                    |  MinLengthRule                 | `String`           | Minimum Length of the string |
| @MaxLength(value)                    |  MaxLengthRule                 | `String`           | Maximum Length of the string |
| @WebUrl(httpRequired)                |  WebUrlRule                    | `String`           | Validates a Web Url          |
| @IntegerRange(min,max)               |  IntegerRangeRule              | `Integer`          | Validates an `Integer` to be within range|
| @FloatRange(min,max)                 |  FloatRangeRule                | `Float`            | Validates a `Float` to be within range|
| @IsChecked                           |  IsCheckedRule                 | `Boolean`          | Validates a `Boolean` to be true|
| @Optional                            |  -                             | `Any`              | Marks the field as an optional field|


## Custom Annotations

If you ever feel the need to create your own validation rules, the library allows you to do just that using the following annotations.

|               Annotation              |          Description         |
| :------------------------------------ |----------------------------: |
| `@FieldValidation(type,rule)`         | You can use this annotation to create your own Validation annotations| 
| `@DynamicOptional(evaluator)`         | You can use this annotation to evaluate the optional flag dynamically| 

Please check out the full guide on custom validations [here](./customValidation)

### Manual Validation using built-in rules

Sometimes, you just need to validate a field value manuall and don't want to build an entire form for that. Or maybe, you want to reuse the validation rules from the library or the ones you've created. In such cases, you can take advantage of the `ValidationRule` instances of the library.

```kotlin title="Using built-in rules"
etProfilePage.doAfterTextChanged { input ->
    val result = WebUrlRule.validate(input, WebUrl(httpRequired = false))
    if (result is FieldResult.Success) {
        ...
        }
    else {
        ...
    }
}
```

```kotlin title="Using custom rules"
object FutureDateRule: StatelessValidationRule<LocalDate, FutureDate> {
    override fun validiate(input: LocalDate, options: FutureDate): FieldResult {
        if (input.isBefore(LocalDate.now())) {
            return FieldResult.Success
        } else {
            return FieldResult.Error("Date must be a future date", this)
        }
    }
}

// Call site
val date = calendarDialog.requestDate()
val result = FutureDateRule.validate(date, FutureDate())
```