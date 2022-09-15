package com.profilecreator.repository

import android.util.Log
import com.google.gson.Gson
import com.profilecreator.dataSource.APIDataSource
import com.profilecreator.dataSource.Result
import com.profilecreator.model.*
import com.profilecreator.network.APIServices
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

            val api = apiDataSource.getRetrofit().create(APIServices::class.java)
            val call = api.generateOTP(generateOTPRequest)

            call.enqueue(object : Callback<GenerateOTPResponse> {
                override fun onFailure(call: Call<GenerateOTPResponse>, throwable: Throwable) {
                    Log.i("onFailure",""+throwable.stackTrace)
                }

                override fun onResponse(call: Call<GenerateOTPResponse>, response: Response<GenerateOTPResponse>) {
                    if(response.isSuccessful) {
                        response.body().also {
                            if (it != null) {
                                generateOTPResponse = it
                            }
                        }
                    } else {

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

            val api = apiDataSource.getRetrofit().create(APIServices::class.java)
            val call = api.confirmOTP(confirmOTPRequest)

            call.enqueue(object : Callback<ConfirmOTPResponse> {
                override fun onFailure(call: Call<ConfirmOTPResponse>, throwable: Throwable) {
                    Log.i("onFailure",""+throwable.stackTrace)
                }

                override fun onResponse(call: Call<ConfirmOTPResponse>, response: Response<ConfirmOTPResponse>) {
                    if(response.isSuccessful) {
                        response.body().also {
                            if (it != null) {
                                confirmOTPResponse = it
                            }
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
