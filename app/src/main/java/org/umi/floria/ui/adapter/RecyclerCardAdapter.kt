package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import org.umi.floria.R

class RecyclerCardAdapter(private val cardItems: List<CardItem>) :
    RecyclerView.Adapter<RecyclerCardAdapter.RecyclerCardViewHolder>() {

    data class CardItem(val imageResource: Int)

    inner class RecyclerCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: MaterialCardView = itemView.findViewById(R.id.card_view)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_card_view, parent, false)
        return RecyclerCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerCardViewHolder, position: Int) {
        val cardItem = cardItems[position]

        // Set gambar untuk setiap item
        holder.imageView.setImageResource(cardItem.imageResource)

        // Menambahkan listener untuk menangani klik pada item (opsional)
        holder.cardView.setOnClickListener {
            // Lakukan sesuatu ketika item diklik
        }
    }

    override fun getItemCount(): Int {
        return cardItems.size
    }
}
