package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R

class HomeSliderAdapter (private val items: List<SliderItem>) :
    RecyclerView.Adapter<HomeSliderAdapter.HomeSliderViewHolder>() {

    // membuat variabel untuk mengisi slider item
    data class SliderItem(val backgroundImageResId: Int, val title: String)
    inner class HomeSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // define view untuk isi dari slidernya
        val backgroundImageView: ImageView = itemView.findViewById(R.id.image_main_bottom_slider)
        val titleTextView: TextView = itemView.findViewById(R.id.text_main_bottom_slider)
    }

    // override createView agar memunculkan VIEW slider pada layout yang dituju R.layout.nama_layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_bottom_slider,
            parent,
            false
        )
        return HomeSliderViewHolder(view)
    }

    // override onBindViewHolder agar recyclerviewnya bisa mengisi data didalam view slider
    override fun onBindViewHolder(holder: HomeSliderViewHolder, position: Int) {
        val item = items[position]
        holder.backgroundImageView.setImageResource(item.backgroundImageResId)
        holder.titleTextView.text = item.title
    }

    // mengembalikan data items.size (size atau jumlah item didalam slider ada berapa)
    override fun getItemCount(): Int {
        return items.size
    }

    }