package me.naingaungluu.formconuctor.sample.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.naingaungluu.formconuctor.sample.data.LoginDataSource
import me.naingaungluu.formconuctor.sample.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
