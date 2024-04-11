package com.eldercare.data

import com.eldercare.data.model.LoggedInUser
import com.eldercare.network.APIInterface
import com.pflegedigital.data.constants.NetworkConstants
import com.pflegedigital.data.model.GeneralResponse
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource, val network: APIInterface?) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, param: Callback<GeneralResponse?>) {
        val call = network?.login(NetworkConstants.BASE_URL+"checkuser/${username}/${password}")
        call?.enqueue(param)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}