package com.profilecreator.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class GenerateOTPRequest(

    @SerializedName("mobile")
    @Expose
    val mobile: String
)

