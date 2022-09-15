package com.profilecreator.network

import com.profilecreator.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface WebService {

    @Headers("Content-Type: application/json")
    @POST("v2/auth/public/generateOTP")
    fun generateOTP(@Body generateOTPRequest: JSONObject): Call<GenerateOTPResponse>

    @Headers("Content-Type: application/json")
    @POST("v2/auth/public/confirmOTP")
    fun confirmOTP(@Body confirmOTPRequest: JSONObject): Call<ConfirmOTPResponse>

    companion object {
        val BASE_URL = "https://cdn-api.co-vin.in/api/"
    }
}