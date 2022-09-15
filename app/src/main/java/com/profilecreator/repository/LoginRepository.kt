package com.profilecreator.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.profilecreator.dataSource.APIDataSource
import com.profilecreator.dataSource.Result
import com.profilecreator.model.*
import com.profilecreator.network.APIServices
import com.profilecreator.network.ServerConnection
import com.profilecreator.ui.util.MainApplication
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


/**
 * Class that requests mobile number for authentication
 * */

class LoginRepository (private val apiDataSource: APIDataSource) {

    fun generateOTP(mobile: String): Result<GenerateOTPResponse> {
        try {
            val generateOTPRequest = GenerateOTPRequest(mobile)
            var generateOTPResponse = GenerateOTPResponse("")

            var gsonString = Gson().toJson(generateOTPRequest)
            var jsonObject = JSONObject(gsonString)

            val api = apiDataSource.getRetrofit().create(APIServices::class.java)
            val call = api.generateOTP(jsonObject)

            call.enqueue(object : Callback<GenerateOTPResponse> {
                override fun onFailure(call: Call<GenerateOTPResponse>, throwable: Throwable) {
                    Log.i("onFailure",""+throwable.stackTrace)
                }

                override fun onResponse(call: Call<GenerateOTPResponse>, successResponse: Response<GenerateOTPResponse>) {
                    successResponse.body().also {
                        if (it != null) {
                            generateOTPResponse = it
                        }
                    }
                }
            })

            return Result.Success(generateOTPResponse)

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun confirmOTP(otp: String, txnId : String): Result<ConfirmOTPResponse> {
        try {
            val confirmOTPRequest = ConfirmOTPRequest(otp, txnId)
            var confirmOTPResponse = ConfirmOTPResponse("", "", "")

            var gsonString = Gson().toJson(confirmOTPRequest)
            var jsonObject = JSONObject(gsonString)

            val api = apiDataSource.getRetrofit().create(APIServices::class.java)
            val call = api.confirmOTP(jsonObject)

            call.enqueue(object : Callback<ConfirmOTPResponse> {
                override fun onFailure(call: Call<ConfirmOTPResponse>, throwable: Throwable) {
                    Log.i("onFailure",""+throwable.stackTrace)
                }

                override fun onResponse(call: Call<ConfirmOTPResponse>, successResponse: Response<ConfirmOTPResponse>) {
                    successResponse.body().also {
                        if (it != null) {
                            confirmOTPResponse = it
                        }
                    }
                }
            })

            return Result.Success(confirmOTPResponse)

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }
}
