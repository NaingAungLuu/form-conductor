package me.naingaungluu.formconuctor.sample.ui.login

import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.MinLength

data class LoginFormData(

    @EmailAddress
    val username: String = "",

    @MinLength(8)
    val password: String = ""
)
