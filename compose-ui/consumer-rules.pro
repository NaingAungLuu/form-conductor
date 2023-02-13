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