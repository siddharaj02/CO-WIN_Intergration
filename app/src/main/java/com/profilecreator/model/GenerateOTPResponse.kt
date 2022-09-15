package com.profilecreator.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class GenerateOTPResponse(

    @SerializedName("txnId")
    @Expose
    var txnId :String

    /*,

    @SerializedName("error")
    @Expose
    val error :String,

    @SerializedName("errorCode")
    @Expose
    val errorCode :String*/
)

