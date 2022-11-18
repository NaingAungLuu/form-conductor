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