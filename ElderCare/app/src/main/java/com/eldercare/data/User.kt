package com.pflegedigital.data.model.user

import java.io.Serializable

data class User(
   val id: String = "",
   val firstName: String = "",
   val lastName: String = "",
   val mobile: String = "",
   val maiden_name: String = "",
   val middleName: String = "",
   val email: String = "",
   val username: String = "",
   val role: String = "",
): Serializable
