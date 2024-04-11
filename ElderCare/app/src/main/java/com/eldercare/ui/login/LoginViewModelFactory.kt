package com.eldercare.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eldercare.data.LoginDataSource
import com.eldercare.data.LoginRepository
import com.eldercare.network.APIClient
import com.eldercare.network.APIInterface


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
                    dataSource = LoginDataSource(),
                    network = APIClient().getClient()?.create<APIInterface>(APIInterface::class.java)
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}