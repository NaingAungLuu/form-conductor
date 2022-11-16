-keep class me.naingaungluu.formconductor.composeui.* { *; }
-keep @me.naingaungluu.formconductor.annotations.* public class *
-keep @me.naingaungluu.formconductor.annotations.Form public class * { *; }
-keep class me.naingaungluu.formconductor.validation.* { *; }
-keepclassmembers class * extends me.naingaungluu.formconductor.validation.ValidationRule {
    public static <fields>;
 }
-keep class me.naingaungluu.formconductor.* { *; }
-keep class me.naingaungluu.formconductor.annotations.* { *; }
-keepattributes RuntimeVisibleAnnotations
-keep class * {
    @me.naingaungluu.formconductor.annotations.* *;
}