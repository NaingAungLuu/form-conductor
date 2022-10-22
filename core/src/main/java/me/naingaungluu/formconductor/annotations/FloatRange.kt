package me.naingaungluu.formconductor.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class FloatRange(
    val start: Double,
    val end: Double
)
