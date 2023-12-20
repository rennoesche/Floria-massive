package org.umi.floria.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter(private val dateList: List<Date>, private val context: Context) :
    RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDay: TextView = itemView.findViewById(R.id.tanggal)
        val textViewTanggal: TextView = itemView.findViewById(R.id.tanggal_angka)
        val viewActiveIndicator: View = itemView.findViewById(R.id.viewActiveIndicator)
    }

    private var activePosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pengingat, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = SimpleDateFormat("dd", Locale.getDefault())
        holder.textViewTanggal.text = day.format(dateList[position])
        val date = dateList[position]
        val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
        val dayString = dayFormat.format(date)

        holder.textViewDay.text = dayString
        holder.viewActiveIndicator.visibility =
            if (position == activePosition) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setActivePositionForToday() {
        // Menentukan posisi yang aktif berdasarkan hari ini
        val today = Calendar.getInstance().time
        activePosition = dateList.indexOfFirst { calendar ->
            // Membandingkan tanggal dari dateList dengan tanggal hari ini
            calendar.isSameDay(today)
        }

        notifyDataSetChanged()
    }

    // Fungsi untuk memeriksa apakah dua tanggal berada pada hari yang sama
    private fun Date.isSameDay(otherDate: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = this
        cal2.time = otherDate
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
    }
}



