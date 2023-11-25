package org.umi.floria.ui

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        WindowCompat.setDecorFitsSystemWindows(window, false)
        @LayoutRes
        fun getLayoutResourceId(): Int {
            return org.umi.floria.R.layout.activity_splash
        }

        setContentView(getLayoutResourceId())

        val splashScreenDuration = 2500L
        val mainActivityIntent = Intent(this, WelcomingActivity::class.java)

        Thread {
            Thread.sleep(splashScreenDuration)
            startActivity(mainActivityIntent)
            finish()
        }.start()
    }
}