package com.profilecreator.ui.util

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.profilecreator.model.ProfileDetails


class Util {
    companion object {

        fun navigateToNextScreen(button: Button, fragmentResourceId: Int) {
            Navigation.findNavController(button).navigate(fragmentResourceId)
        }

        fun showResult(applicationContext: Context, text: String) {
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }

        internal fun initProfileDetails() {
            val prefs = MainApplication.applicationContext().getSharedPreferences("PersonalDetails", 0)
            val string = prefs?.getString("PersonalDetails", "")
            if (string != null) {
                if (string.isNotEmpty())
                    MainApplication.profileDetails = Gson().fromJson(string, ProfileDetails::class.java)
            }
        }


    }
}