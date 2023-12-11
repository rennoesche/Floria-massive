package org.umi.floria.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import org.umi.floria.R
import org.umi.floria.models.VideoData
import org.umi.floria.ui.adapter.TopCarouselAdapter
import org.umi.floria.ui.adapter.VideoAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assuming you have a RecyclerView in fragment_tips.xml with the ID videoRecyclerView
        val videoRecyclerView: RecyclerView = view.findViewById(R.id.tips_recycler_view)

        // Assuming you have a list of VideoData
        val videoDataList = getVideoDataList()

        // Create and set up the VideoAdapter
        val videoAdapter = VideoAdapter(videoDataList)
        videoRecyclerView.adapter = videoAdapter
    }

    // Replace this function with your logic to get a list of VideoData
    private fun getVideoDataList(): List<VideoData> {
        // Replace this with your actual data retrieval logic
        // For now, using dummy data
        return listOf(
            VideoData("Video 1", "thumbnail_url_1", "Channel 1"),
            VideoData("Video 2", "thumbnail_url_2", "Channel 2"),
            VideoData("Video 3", "thumbnail_url_3", "Channel 3")
        )
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