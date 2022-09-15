package com.profilecreator.dataSource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.profilecreator.model.ProfileDetails
import com.profilecreator.network.APIServices
import com.profilecreator.ui.util.MainApplication
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * To store json on cloud, I have used https://jsonbin.io/ instead of Gist
 * Here is documentation of jsonbin.io
 * https://jsonbin.io/api-reference
 *
 * Here is a url to check stored json in browser window:
 * https://api.jsonbin.io/b/5d28930e6e599f247d56d0f7/latest
 * */

class ProfileDataSource {

    //get user profile to server
    fun saveProfileInfo(): LiveData<ProfileDetails>? {
        var profileDetailsMutableLiveData = MutableLiveData<ProfileDetails>()

        try {
            var gsonString = Gson().toJson(MainApplication.profileDetails)
            var jsonObject = JSONObject(gsonString)

            try {
                setPersonalDetails()
            } catch (e: Exception) {
            }

            val api = getRetrofit().create(APIServices::class.java)
            val call = api.saveProfile(jsonObject)

            call.enqueue(object : Callback<ProfileDetails> {
                override fun onFailure(call: Call<ProfileDetails>, throwable: Throwable) {

                    profileDetailsMutableLiveData.value?.exception = throwable as Exception
                }

                override fun onResponse(call: Call<ProfileDetails>, successResponse: Response<ProfileDetails>) {
                    val profileDetails = successResponse.body()
                    if (profileDetails != null) profileDetails.also { profileDetailsMutableLiveData.value = it }
                    else {
                        throw NullPointerException("Expression 'profileDetails' must not be null")
                    }
                }
            })

            profileDetailsMutableLiveData.value = MainApplication.profileDetails

            return profileDetailsMutableLiveData

        } catch (e: Throwable) {
            MainApplication.profileDetails.exception = e as Exception
            profileDetailsMutableLiveData.value = MainApplication.profileDetails
            return profileDetailsMutableLiveData
        }
    }

    //get retrofit instance
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIServices.BASE_URL_JSON_BIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //save profile to shared preferences for offline usage
    private fun setPersonalDetails() {
        val editor =
                MainApplication.applicationContext().getSharedPreferences("PersonalDetails", Context.MODE_PRIVATE)
                        ?.edit()
        editor?.putString("PersonalDetails", Gson().toJson(MainApplication.profileDetails))
        editor?.commit()
    }
}