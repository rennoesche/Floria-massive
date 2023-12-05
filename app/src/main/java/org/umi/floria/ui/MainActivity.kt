package org.umi.floria.ui

import android.os.Bundle
import android.view.Menu
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

        // set icon navbar ketika dipilih/selected dan mengatur navigasi fragment

        bottomNav.setOnItemSelectedListener { item ->
            resetAllIcons(bottomNav.menu)
            when (item.itemId) {
                R.id.beranda -> {
                    item.setIcon(R.drawable.lg_floria)
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.tips -> {
                    item.setIcon(R.drawable.def_tips)
                    replaceFragment(TipsFragment())
                    true
                }

                R.id.konsultasi -> {
                    replaceFragment(KonsultasiFragment())
                    true
                }

                R.id.pengingat -> {
                    item.setIcon(R.drawable.def_reminder)
                    replaceFragment(PengingatFragment())
                    true
                }

                R.id.profil -> {
                    item.setIcon(R.drawable.def_profil)
                    replaceFragment(ProfilFragment())
                    true
                }

                else -> false
            }
        }

        replaceFragment(HomeFragment())
    }

    // fungsi untuk mengganti fragment home ketika icon navbar dipilih
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // reset icon navbar
    private fun resetAllIcons(menu: Menu) {
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            when (menuItem.itemId) {
                R.id.beranda -> menuItem.setIcon(R.drawable.def_logo_inactive)
                R.id.tips -> menuItem.setIcon(R.drawable.def_tips_inactive)
                R.id.pengingat -> menuItem.setIcon(R.drawable.def_reminder_inactive)
                R.id.profil -> menuItem.setIcon(R.drawable.def_profil_inactive)
            }
        }
    }
}
