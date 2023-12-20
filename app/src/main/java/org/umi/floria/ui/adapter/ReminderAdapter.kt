package org.umi.floria.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R
import org.umi.floria.room.Reminder
import java.text.SimpleDateFormat
import java.util.Date

class ReminderAdapter(private var reminders: List<Reminder>) : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judul: TextView = itemView.findViewById(R.id.title_text_today)
        val subJudul: TextView = itemView.findViewById(R.id.subtitle_text_today)
        val suppText: TextView = itemView.findViewById(R.id.supporting_text_right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pengingat_today, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentReminder = reminders[position]
        val x = Date(currentReminder.activityDate)
        val formatX = "dd-MM-yyyy"
        holder.judul.text = currentReminder.plantName
        holder.subJudul.text = currentReminder.requiredMaterials
        holder.suppText.text = SimpleDateFormat(formatX).format(x).toString()
        // Setel TextView lain sesuai kebutuhan
    }


    override fun getItemCount(): Int {
        return reminders.size
    }
    fun setData(newReminders: List<Reminder>) {
        reminders = newReminders
        notifyDataSetChanged()
    }
}
