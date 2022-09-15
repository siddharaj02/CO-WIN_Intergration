package com.profilecreator.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    @SerializedName("txnId")
    @Expose
    val mobileNumber: String,

    @SerializedName("txnId")
    @Expose
    val otp : String,

    @SerializedName("txnId")
    @Expose
    var token :String,

    @SerializedName("txnId")
    @Expose
    var txnId :String,

    @SerializedName("error")
    @Expose
    val error :String,

    @SerializedName("errorCode")
    @Expose
    val errorCode :String
) {
    constructor(mobileNumber: String) : this(mobileNumber, "","", "", "", "")
    constructor(mobileNumber: String, otp : String) : this(mobileNumber, otp,"", "" , "", "")
}

