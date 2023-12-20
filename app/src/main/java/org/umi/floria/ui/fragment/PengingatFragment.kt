package org.umi.floria.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.umi.floria.R
import org.umi.floria.ui.adapter.CalendarAdapter
import org.umi.floria.ui.adapter.ReminderAdapter
import org.umi.floria.vm.ReminderViewModel
import java.text.SimpleDateFormat
import java.util.*

class PengingatFragment : Fragment() {

    private lateinit var reminderAdapter: ReminderAdapter
    private lateinit var viewModel: ReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pengingat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reminderAdapter = ReminderAdapter(emptyList())

        val textViewMonth: TextView = view.findViewById(R.id.bulan)
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        textViewMonth.text = monthFormat.format(Date())

        val textViewYear: TextView = view.findViewById(R.id.tahun)
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        textViewYear.text = yearFormat.format(Date())

        val listTanggal = getDateList()

        val rvDate: RecyclerView = view.findViewById(R.id.datetime_pengingat)
        rvDate.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CalendarAdapter(listTanggal, requireContext())
        rvDate.adapter = adapter
        adapter.setActivePositionForToday()

        val recyclerView: RecyclerView = view.findViewById(R.id.pengingat_recycler_view_today)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = reminderAdapter

        viewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)

        viewModel.reminder.observe(viewLifecycleOwner) { reminders ->
            reminderAdapter.setData(reminders)
        }

        viewModel.getReminderList(requireContext())

        val addReminderFAB: FloatingActionButton = view.findViewById(R.id.tambahpengingat)
        addReminderFAB.setOnClickListener {
            addPengingat()
        }
    }

    private fun addPengingat() {
        val addReminder = AddReminder()
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, addReminder).addToBackStack(null).commit()
    }

    private fun getDateList(): List<Date> {
        val calendar = Calendar.getInstance()
        val dateList = mutableListOf<Date>()

        // Set ke hari Senin
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        // Mendapatkan tanggal untuk satu minggu ke depan
        for (i in 1..7) {
            // Menambahkan tanggal ke dalam daftar
            dateList.add(calendar.time)

            // Pindah ke hari berikutnya
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dateList
    }
}
