package com.profilecreator.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.co_win_intergration.R
import com.profilecreator.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    private val SPLASH_MILLIS: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(
            R.layout.activity_splash, null, false
        ) as RelativeLayout

        addContentView(
            layout, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        val handler = Handler()

        handler.postDelayed({
            val intent = Intent(
                this@SplashActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
            finish()
        }, SPLASH_MILLIS)

    }
}