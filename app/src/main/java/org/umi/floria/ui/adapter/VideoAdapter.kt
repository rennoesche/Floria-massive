package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.umi.floria.R
import org.umi.floria.models.VideoData

class VideoAdapter(private val videoList: List<VideoData>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val titleName: TextView = itemView.findViewById(R.id.title_name)
        val channelName: TextView = itemView.findViewById(R.id.channel_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tips_item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoData = videoList[position]

        // Load thumbnail using Picasso library
        Picasso.get().load(videoData.thumbnails).into(holder.thumbnail)

        holder.titleName.text = videoData.title
        holder.channelName.text = videoData.channelName
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}

