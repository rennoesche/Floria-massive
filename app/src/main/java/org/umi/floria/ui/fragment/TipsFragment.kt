package org.umi.floria.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import org.umi.floria.R
import org.umi.floria.ui.adapter.TopCarouselAdapter

class TipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tips, container, false)

        val carouselRecyclerView: RecyclerView = view.findViewById(R.id.carousel_recycler_view)
        carouselRecyclerView.layoutManager = CarouselLayoutManager()

        val adapter = TopCarouselAdapter(getDummyData()) // mengambil data dummy dibawah agar ditampilkan dalam fragment
        carouselRecyclerView.adapter = adapter

        return view
    }

    // mengambil data dummy didalam list
    private fun getDummyData(): List<TopCarouselAdapter.CarouselItem> {
        return listOf(
            TopCarouselAdapter.CarouselItem(R.drawable.howto_a, "Cara merawat tanaman hias lidah mertua"),
            TopCarouselAdapter.CarouselItem(R.drawable.howto_a, "Cara merawat tanaman hias lidah mertua"),
            TopCarouselAdapter.CarouselItem(R.drawable.howto_a, "Cara merawat tanaman hias lidah mertua")
        )
    }

}
