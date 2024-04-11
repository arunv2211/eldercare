package com.eldercare.network

import com.pflegedigital.data.model.GeneralResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface APIInterface {
    @GET()
    fun login(@Url url: String): Call<GeneralResponse?>?
}