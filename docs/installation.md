---
title: Installation
description: How to install form-conductor dependency to your project
icon: material/newspaper
---

# Installation

#### Latest Version

![JitPack](https://img.shields.io/jitpack/version/com.github.NaingAungLuu/form-conductor?style=for-the-badge)

### Single dependency (imports all the modules as a single dependency)


=== "Groovy"

    ```groovy
    implementation "com.github.NaingAungLuu:form-conductor:0.2.3"
    ```

=== "Kts"

    ```kotlin
    implementation("com.github.NaingAungLuu:form-conductor:0.2.3")
    ```

Note: If the documentation is out of sync, please use `com.github.NaingAungLuu:form-conductor:<version>"`


### Modular dependency

=== "Groovy"

    ```groovy
    def formconductor_version = "0.2.3"
    implementation "com.github.NaingAungLuu.form-conductor:core:$formconductor_version"
    implementation "com.github.NaingAungLuu.form-conductor:compose-ui:$formconductor_version"
    ```

=== "Kts"

    ```kotlin
    val formConductorVersion = "0.2.3"
    implementation("com.github.NaingAungLuu.form-conductor:core:$formconductor_version")
    implementation("com.github.NaingAungLuu.form-conductor:compose-ui:$formconductor_version")
    ```
    

### Available Modules

`form-conductor:core` - Pure Kotlin library with all form validation features<br>
`form-conductor:compose-ui` - Android library with `form` and `field` composables with scopes for easy form state handling in Jetpack Compose.


## A little note on R8/Proguard on Android Builds

Since we're using kotlin-reflect to process form data classes, R8/Proguard on android tends to strip off the constructors and metadata information from those classes during the obfuscation/optimization phase. To instruct proguard to spare our form data classes, we've added [`consumer-rules.pro`](https://github.com/NaingAungLuu/form-conductor/blob/master/compose-ui/consumer-rules.pro) file to `compose-ui` artifact. 

You don't have to worry about proguard stripping off your form data classes in release builds on Android. It'll just work out-of-the-box starting from version `0.2.3`.

However, if you're using just `com.github.NaingAungLuu.form-conductor:core` without `compose-ui` in your Android Project, you'll need to include the following proguard rules in your app module's `proguard-rules.pro` file.

```pro
-keep class me.naingaungluu.formconductor.composeui.* { *; }
-keep class kotlin.reflect.jvm.internal.** { *; }
-keep class kotlin.Metadata { *; }
-keep class me.naingaungluu.formconductor.validation.* { *; }
-keepclassmembers class * extends me.naingaungluu.formconductor.validation.ValidationRule {
    public static <fields>;
 }
-keep class me.naingaungluu.formconductor.annotations.* { *; }
-keepattributes RuntimeVisibleAnnotations
-keep class * {
    <init>(...);
    @me.naingaungluu.formconductor.annotations.* *;
}
-keep @me.naingaungluu.formconductor.annotations.** class * { *; }
-keep @me.naingaungluu.formconductor.annotations.Form class * {
   *;
 }
-keep,allowobfuscation class me.naingaungluu.formconductor.**
```

