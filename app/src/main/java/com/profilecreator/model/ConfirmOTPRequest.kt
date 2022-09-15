package com.profilecreator.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class ConfirmOTPRequest(
    @SerializedName("otp")
    @Expose
    val otp : String,

    @SerializedName("txnId")
    @Expose
    var txnId :String


)

