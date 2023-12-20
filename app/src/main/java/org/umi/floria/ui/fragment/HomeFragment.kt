package org.umi.floria.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import kotlinx.coroutines.Dispatchers
import org.umi.floria.R
import org.umi.floria.ui.adapter.HomeSliderAdapter
import org.umi.floria.ui.adapter.RecyclerCardAdapter
import org.umi.floria.room.AppDatabase
import org.umi.floria.room.User
import org.umi.floria.room.UserDao
import org.umi.floria.models.PreferencesManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment home
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // mendefinisikan variabel carouser dan card
        val carouselRecyclerView: RecyclerView = view.findViewById(R.id.home_slider)
        val cardRecyclerView: RecyclerView = view.findViewById(R.id.view_card)
        cardRecyclerView.layoutManager = LinearLayoutManager(context)
        carouselRecyclerView.layoutManager = CarouselLayoutManager()

        val adapter = HomeSliderAdapter(getData())
        val x = RecyclerCardAdapter(getCard())
        cardRecyclerView.adapter = x
        carouselRecyclerView.adapter = adapter


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textWelcomingUser = view.findViewById<TextView>(R.id.welcoming_user)

        val preferencesManager = PreferencesManager(requireContext())
        val userId = preferencesManager.getUserId()

        lifecycleScope.launch(Dispatchers.IO) {
            val userDao = AppDatabase.getInstance(requireContext()).userDao()
            val userName = userDao.getUserById(userId)

            withContext(Dispatchers.Main) {
                textWelcomingUser.text = "$userName!"
            }
        }
    }

    // mengambil data card dengan cara list
    private fun getCard(): List<RecyclerCardAdapter.CardItem> {
        return listOf(
            RecyclerCardAdapter.CardItem(R.drawable.bg_card1),
            RecyclerCardAdapter.CardItem(R.drawable.bg_card2),
            RecyclerCardAdapter.CardItem(R.drawable.bg_card1)
        )
    }

    // mengambil data slider dengan cara list
    private fun getData(): List<HomeSliderAdapter.SliderItem> {
        return listOf(
            HomeSliderAdapter.SliderItem(R.drawable.slider_1, "Bunga"),
            HomeSliderAdapter.SliderItem(R.drawable.slider_1, "Pohon"),
            HomeSliderAdapter.SliderItem(R.drawable.slider_1, "Menyiram")
        )
    }
}
