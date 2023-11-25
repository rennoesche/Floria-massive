package org.umi.floria.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.umi.floria.ui.fragment.Slide1Fragment
import org.umi.floria.ui.fragment.Slide2Fragment
import org.umi.floria.ui.fragment.Slide3Fragment
import org.umi.floria.ui.fragment.Slide4Fragment


class WelcomingPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Slide1Fragment()
            1 -> Slide2Fragment()
            2 -> Slide3Fragment()
            3 -> Slide4Fragment()
            // Tambahkan fragment lain sesuai kebutuhan
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 4
    }
}
