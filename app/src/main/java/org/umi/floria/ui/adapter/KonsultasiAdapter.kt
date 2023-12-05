package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R

class KonsultasiAdapter(private val dataSet: List<Any>) :
    RecyclerView.Adapter<KonsultasiAdapter.KonsultasiViewHolder>() {

    class KonsultasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonsultasiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_konsultasi_card, parent, false)
        return KonsultasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: KonsultasiViewHolder, position: Int) {
        // Bind data to the item view (if needed)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
