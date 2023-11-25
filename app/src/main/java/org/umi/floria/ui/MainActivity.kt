package org.umi.floria.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.umi.floria.R
import org.umi.floria.ui.fragment.HomeFragment
import org.umi.floria.ui.fragment.KonsultasiFragment
import org.umi.floria.ui.fragment.PengingatFragment
import org.umi.floria.ui.fragment.ProfilFragment
import org.umi.floria.ui.fragment.TipsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.beranda -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.tips -> {
                    replaceFragment(TipsFragment())
                    true
               }
                R.id.konsultasi -> {
                    replaceFragment(KonsultasiFragment())
                    true
                }
                R.id.pengingat -> {
                    replaceFragment(PengingatFragment())
                    true
                }
                R.id.profil -> {
                    replaceFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }

        replaceFragment(HomeFragment())
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
