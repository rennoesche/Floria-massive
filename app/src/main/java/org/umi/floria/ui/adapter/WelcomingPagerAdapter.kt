package org.umi.floria.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.umi.floria.ui.fragment.*


class WelcomingPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // mendefinisikan banyak item fragmen agar bisa di loop
    override fun getItemCount(): Int {
        return 4
    }
    // membuat fragmen adapter, jika posisi item sama dengan 0, maka akan menampilkan fragmen slide1, dst
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Slide1Fragment()
            1 -> Slide2Fragment()
            2 -> Slide3Fragment()
            3 -> Slide4Fragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
