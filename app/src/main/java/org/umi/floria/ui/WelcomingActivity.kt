package org.umi.floria.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.umi.floria.R
import org.umi.floria.models.PreferencesManager
import org.umi.floria.ui.adapter.WelcomingPagerAdapter

class WelcomingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferencesManager(this)

        val nextActivity = preferencesManager.determineNextActivity()

        if (nextActivity == WelcomingActivity::class.java) {
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
                preferencesManager.isFreshInstall = preferencesManager.isFreshInstall.not()
                val nextActivity = preferencesManager.determineNextActivity()
                val intent = Intent(this, nextActivity)
                startActivity(intent)
                finish()
            }
        } else {
            val intent = Intent(this, nextActivity)
            startActivity(intent)
            finish()
        }
    }

    private fun updateIndicator(position: Int) {
        for (i in 0 until indicatorLayout.childCount) {
            val circle = indicatorLayout.getChildAt(i) as android.view.View
            circle.setBackgroundResource(if (i == position) R.drawable.sel_circle_bg else R.drawable.circle_bg)
        }
    }
}
