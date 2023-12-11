package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R
import org.umi.floria.models.GridItem

class KonsultasiAdapter(private val items: List<GridItem>) :
    RecyclerView.Adapter<KonsultasiAdapter.KonsultasiViewHolder>() {

    inner class KonsultasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.nama_konsul)
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsi_job)
        val fee: TextView = itemView.findViewById(R.id.fee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonsultasiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_konsultasi, parent, false)
        return KonsultasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: KonsultasiViewHolder, position: Int) {
        val item = items[position]
        holder.nama.text = item.nama
        holder.deskripsi.text = item.description
        holder.fee.text = item.fee
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
