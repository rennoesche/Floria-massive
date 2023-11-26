package org.umi.floria.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.umi.floria.R
import org.umi.floria.ui.adapter.WelcomingPagerAdapter

class WelcomingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("FreshInstall", true)) {
            setContentView(R.layout.activity_welcoming)
            val btnStart: Button = findViewById(R.id.btnStart)
            indicatorLayout = findViewById(R.id.indicatorLayout)

            viewPager = findViewById(R.id.viewPager)

            val adapter = WelcomingPagerAdapter(this)
            viewPager.adapter = adapter

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    updateIndicator(position)
                }
            })

            btnStart.setOnClickListener {
                setFreshInstall(false)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    //    private fun isFreshInstall(): Boolean {
//        return sharedPreferences.getBoolean("FreshInstall", true)
//    }

    private fun updateIndicator(position: Int) {
        for (i in 0 until indicatorLayout.childCount) {
            val circle = indicatorLayout.getChildAt(i) as View
            circle.setBackgroundResource(if (i == position) R.drawable.sel_circle_bg else R.drawable.circle_bg)
        }
    }

    private fun setFreshInstall(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("FreshInstall", value)
        editor.apply()
    }
}

