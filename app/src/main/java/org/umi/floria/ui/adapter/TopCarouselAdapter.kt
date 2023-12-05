package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R

class TopCarouselAdapter(private val items: List<CarouselItem>) :
    RecyclerView.Adapter<TopCarouselAdapter.TopCarouselViewHolder>() {

    // membuat dataclass isi dari slider berupa bg_res_id dan title
    data class CarouselItem(val backgroundImageResId: Int, val title: String)

    inner class TopCarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val backgroundImageView: ImageView = itemView.findViewById(R.id.carousel_image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.carousel_title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.tips_top_carousel,
            parent,
            false
        )
        return TopCarouselViewHolder(view)
    }

    // mengisi data didalam view berupa gambar dan judul
    override fun onBindViewHolder(holder: TopCarouselViewHolder, position: Int) {
        val item = items[position]
        holder.backgroundImageView.setImageResource(item.backgroundImageResId)
        holder.titleTextView.text = item.title
    }

    override fun getItemCount(): Int {
        return items.size
    }
}