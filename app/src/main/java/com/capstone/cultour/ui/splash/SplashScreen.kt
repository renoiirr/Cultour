package com.capstone.cultour.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import com.capstone.cultour.R
import com.capstone.cultour.ui.MainActivity
import com.capstone.cultour.ui.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000 // Milliseconds


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },SPLASH_TIME_OUT)

    }
}