-keep class me.naingaungluu.formconductor.composeui.* { *; }
-keep class me.naingaungluu.formconductor.validation.* { *; }
-keepclassmembers class * extends me.naingaungluu.formconductor.validation.ValidationRule {
    public static <fields>;
 }
-keep class me.naingaungluu.formconductor.annotations.* { *; }

-keepattributes *Annotation*,Signature,InnerClasses

# Keep classes and properties annotated with @Form
-if @me.naingaungluu.formconductor.annotations.Form class **
-keepclassmembers,allowobfuscation class <1> {
    <init>(...);
    <fields>;
}

# keep all classes annotated with @Form all the classes referenced in that @Form class
-if @me.naingaungluu.formconductor.annotations.Form class ** {
    ** *;
}
-keep class <1> {
    <init>(...);
    <fields>;
}
-keep @me.naingaungluu.formconductor.annotations.** class * { *; }
-keep @me.naingaungluu.formconductor.annotations.Form class * {
   *;
 }
 -keep,allowobfuscation @me.naingaungluu.formconductor.annotations.Form class * {
    *;
  }
-keep class me.naingaungluu.formconductor.** { *; }