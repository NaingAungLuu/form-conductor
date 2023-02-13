package me.naingaungluu.formconductor.builder

import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.annotations.Optional
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class FormObject<T> {
    protected open fun customSetup() {}
    protected infix fun <V> KProperty<V>.isMandatoryWhen(check: () -> Boolean) = run {
        if (check.invoke()) {
            println("do something")
        }
    }
}

annotation class Mandatory<T>(
    val rule: Array<KClass<out MandatoryRule<T>>> = []
)

interface MandatoryRule<T> {
    fun isMandatory(formState: T): Boolean
}

object MandatoryForAdultRule: MandatoryRule<SignUpFormData> {
    override fun isMandatory(formState : SignUpFormData): Boolean = when(formState.city) {
        "US" -> false
        else -> true
    }
}

data class SignUpFormData (
    val city: String = "",

    @Optional
    @MinLength(2)
    @Mandatory<SignUpFormData>(
        rule = [ MandatoryForAdultRule::class ]
    )
    val userName: String = ""
)

class Program {
    fun run() {
    }
}
