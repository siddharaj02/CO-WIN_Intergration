package com.profilecreator.ui.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.profilecreator.model.ProfileDetails

class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null
        var profileDetails: ProfileDetails = ProfileDetails()

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun getInstance(): MainApplication {
            return instance!!
        }

        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected()
        }
    }


    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = applicationContext()
    }
}