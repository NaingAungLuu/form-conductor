---
title: Installation
description: How to install form-conductor dependency to your project
icon: material/newspaper
sidebar_position: 2
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installation

#### Latest Version

![Maven Central](https://img.shields.io/maven-central/v/me.naingaungluu.formconductor/core?color=green&style=for-the-badge)


<Tabs groupId="build-language">
  <TabItem value="groovy" label="Groovy">

```groovy
def formconductor_version = "0.4.0"

implementation "me.naingaungluu.formconductor:core:$formconductor_version"
implementation "me.naingaungluu.formconductor:compose-ui:$formconductor_version"
```

  </TabItem>

  <TabItem value="kotlin" label="Kotlin DSL">

```kotlin
val formConductorVersion = "0.4.0"

implementation("me.naingaungluu.formconductor:core:$formconductor_version")
implementation("me.naingaungluu.formconductor:compose-ui:$formconductor_version")
```

  </TabItem>
</Tabs>
    

### Available Modules

* `form-conductor:core` - Pure Kotlin library with all form validation features
* `form-conductor:compose-ui` - Android library with `form` and `field` composables with scopes for easy form state handling in Jetpack Compose.


## A little note on R8/Proguard on Android Builds

Since we're using kotlin-reflect to process form data classes, R8/Proguard on android tends to strip off the constructors and metadata information from those classes during the obfuscation/optimization phase. To instruct proguard to spare our form data classes, we've added [`consumer-rules.pro`](https://github.com/NaingAungLuu/form-conductor/blob/master/compose-ui/consumer-rules.pro) file to `compose-ui` artifact. 

You don't have to worry about proguard stripping off your form data classes in release builds on Android. It'll just work out-of-the-box starting from version `0.2.3`.

However, if you're using just `me.naingaungluu.formconductor:core` without `compose-ui` in your Android Project, you'll need to include the following proguard rules in your app module's `proguard-rules.pro` file.

```pro
-keepclassmembers class * extends me.naingaungluu.formconductor.validation.ValidationRule {
    <methods>;
    public static <fields>;
 }

# Keep classes and properties annotated with @Form
-if @me.naingaungluu.formconductor.annotations.Form class **
-keepclassmembers,allowobfuscation,allowoptimization class <1> {
    <init>(...);
    <fields>;
}

# keep all the classes referenced in the @Form class
-if @me.naingaungluu.formconductor.annotations.Form class ** {
    ** *;
}
-keep,allowoptimization,allowshrinking class <1> {
    <init>(...);
    <fields>;
}
-keep @me.naingaungluu.formconductor.annotations.Form class * { *; }
-keep class me.naingaungluu.formconductor.** { *; }

# Keep kotlin.Metadata annotations to maintain metadata on kept items
-keepattributes *Annotation*,Signature,InnerClasses
-keep class kotlin.Metadata { *; }
```

