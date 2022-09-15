package com.profilecreator.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class DownloadCertificateResponse(

    @SerializedName("txnId")
    @Expose
    var token :String,

    @SerializedName("error")
    @Expose
    val error :String,

    @SerializedName("errorCode")
    @Expose
    val errorCode :String
) {
   }

