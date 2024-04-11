package com.eldercare.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.eldercare.data.LoginRepository
import com.eldercare.data.Result

import com.eldercare.R
import com.pflegedigital.data.model.GeneralResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password, object : Callback<GeneralResponse?> {
            override fun onResponse(
                call: Call<GeneralResponse?>,
                response: Response<GeneralResponse?>
            ) {
                if(response.body()?.success == true) {
                    _loginResult.value =
                        LoginResult(success = LoggedInUserView(displayName = "Hello ${username}"))
                } else {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }

            override fun onFailure(call: Call<GeneralResponse?>, t: Throwable) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        })
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}