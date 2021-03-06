package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.R

/**
 * SlashActivity class used to inflate Splash screen
 * For two seconds then start the intro screens activity
 *
 */

class SplashActivity : AppCompatActivity() {
    private val localStorage = LocalStorage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        var token: String? = localStorage.getTokenData(this)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent
            if (token!!.isEmpty()) {
                intent = Intent(this, IntroActivity::class.java)

            } else {
                intent = Intent(this, MainScreenActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }
}