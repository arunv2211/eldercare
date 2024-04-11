package com.pflegedigital.data.model

import com.pflegedigital.data.model.user.User

data class GeneralResponse(
    val success: Boolean = false,
    val statusCode: Int = 0,
    val message: String = "",
    val data: User? = null,
    val error:Int? = null
)
