package org.umi.floria.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import org.umi.floria.R

class WelcomingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        if (isFreshInstall()) {
            setContentView(R.layout.activity_welcoming)
            val btnStart: Button = findViewById(R.id.btnStart)

            viewPager = findViewById(R.id.viewPager)

            val adapter = WelcomingPagerAdapter(supportFragmentManager)
            viewPager.adapter = adapter

            btnStart.setOnClickListener {
                setFreshInstall(false)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun isFreshInstall(): Boolean {
        return sharedPreferences.getBoolean("FreshInstall", true)
    }
    private fun setFreshInstall(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("FreshInstall", value)
        editor.apply()
    }
}
