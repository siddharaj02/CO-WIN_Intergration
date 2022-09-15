package com.profilecreator.network

import com.profilecreator.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIServices {

    @POST("/login")
    fun authenticateUser(@Query("email") email: String, @Query("password") password: String): Call<User>

    //POST method is not used because we are updating already stored json data
    @PUT("b/5d28930e6e599f247d56d0f7")  //secret key of jsonbin.io --> 5d28930e6e599f247d56d0f7
    @Headers(
        "Content-Type:application/json",
        "secret-key:\$2a\$10$" + "wHjjA1hNO5ey/eIjZgvehuU6ZqMP4RkAqbmf3x4tlswcd8pBdHmCm"
    )
    fun saveProfile(@Body gson: JSONObject): Call<ProfileDetails>

    @POST("v2/auth/public/generateOTP")
    @Headers("Content-Type: application/json; charset=utf-8")
    fun generateOTP(@Body generateOTPRequest: JSONObject): Call<GenerateOTPResponse>

    @POST("v2/auth/public/confirmOTP")
    @Headers("Content-Type: application/json; charset=utf-8")
    fun confirmOTP(@Body confirmOTPRequest: JSONObject): Call<ConfirmOTPResponse>


    @GET("v2/registration/certificate/public/download")
    fun downloadCertificate(@Body downloadRequest: JSONObject): Call<DownloadCertificateResponse>


    companion object {
        val BASE_URL = "https://cdn-api.co-vin.in/api/"
        val BASE_URL_JSON_BIN = "https://api.jsonbin.io/"
    }
}