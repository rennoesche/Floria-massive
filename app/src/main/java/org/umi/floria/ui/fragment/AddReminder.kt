package org.umi.floria.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.umi.floria.R
import org.umi.floria.room.Reminder
import org.umi.floria.ui.adapter.ReminderAdapter
import org.umi.floria.vm.ReminderViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AddReminder : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar: MaterialToolbar = view.findViewById(R.id.topAppBar)
        topAppBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val waktuAktivitas: TextView = view.findViewById(R.id.waktu_aktivitas)
        waktuAktivitas.setOnClickListener{
            waktuAktivitas()
        }
        val bahanDibutuhkan: TextView = view.findViewById(R.id.required_bahan)
        bahanDibutuhkan.setOnClickListener{
            bahanDibutuhkan()
        }
        val ingatkanSebelum: TextView = view.findViewById(R.id.ingatkan_sebelum)
        ingatkanSebelum.setOnClickListener{
            ingatkanSebelum()
        }
        val tanggalAktivitas: TextView = view.findViewById(R.id.tanggal_aktivitas)
        tanggalAktivitas.setOnClickListener{
            tanggalAktivitas()
        }

        val simpanButton: Button = view.findViewById(R.id.simpan_pengingat)
        simpanButton.setOnClickListener {
            saveReminderToDatabase()
        }

    }

    private fun saveReminderToDatabase() {
        val teksJudul = requireView().findViewById<TextInputLayout>(R.id.judulpengingat).editText
        val teksWaktu = requireView().findViewById<TextView>(R.id.st_waktu_aktivitas)
        val teksBahan = requireView().findViewById<TextView>(R.id.st_bahan_dibutuhkan)
        val teksIngatkan = requireView().findViewById<TextView>(R.id.st_ingatkan_sebelum)
        val teksTanggal = requireView().findViewById<TextView>(R.id.st_tanggal_aktivitas)
        val teksDeskripsi = requireView().findViewById<TextInputLayout>(R.id.deskripsi_pengingat).editText

        // Validasi input
        if (teksJudul?.text?.toString().isNullOrEmpty()) {
            view?.findViewById<TextInputLayout>(R.id.judulpengingat)?.error = "Judul tidak boleh kosong"
            return
        }

        // Mengubah teks waktu menjadi integer
        val waktu = teksWaktu.text?.toString()?.replace(":", "")?.toIntOrNull() ?: 0

        // Mengubah teks tanggal menjadi Long
        val tanggal = teksTanggal.text?.toString()?.toDateMillis() ?: 0

        // Membuat activity created
        val activityCreated = SimpleDateFormat("dd:MM:yyyy:HH:mm", Locale.getDefault()).format(Date()).toDateMillis()

        // Simpan ke database
        val reminder = Reminder(
            plantName = teksJudul?.text.toString(),
            activityTime = waktu,
            requiredMaterials = teksBahan.text?.toString() ?: "",
            reminderBeforeHours = teksIngatkan.text?.toString()?.toIntOrNull() ?: 0,
            activityDate = tanggal,
            activityCreated = activityCreated,
            description = teksDeskripsi?.text.toString()
        )

        val reminderViewModel = ReminderViewModel(requireActivity().application)
        reminderViewModel.insertReminder(reminder)

        // Kembali ke fragment sebelumnya
         MaterialAlertDialogBuilder(requireContext())
            .setTitle("Pengingat ditambahkan")
            .setMessage("Pengingat telah ditambahkan dalam list")
            .setPositiveButton("OK") { _, _ ->
                requireActivity().supportFragmentManager.popBackStack()
            }
            .show()

    }

    // Fungsi ekstensi untuk mengubah teks tanggal menjadi Long
    private fun String.toDateMillis(): Long {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return try {
            dateFormat.parse(this)?.time ?: 0
        } catch (e: ParseException) {
            0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_reminder, container, false)
    }

    private fun waktuAktivitas() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val rootView = requireActivity().findViewById<ViewGroup>(android.R.id.content)
        val overlay = View(requireContext())


        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(hour)
            .setMinute(minute)
            .setTitleText("Pilih waktu Aktivitas")
            .build()

        materialTimePicker.addOnPositiveButtonClickListener {
            val selectedHour = materialTimePicker.hour
            val selectedMinute = materialTimePicker.minute

            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val formattedTime = timeFormat.format(Date().apply {
                hours = selectedHour
                minutes = selectedMinute
            })
            val supportingText: TextView = requireView().findViewById(R.id.st_waktu_aktivitas)
            supportingText.text = formattedTime
            rootView.removeView(overlay)
        }
        materialTimePicker.addOnCancelListener {
            rootView.removeView(overlay)
        }
        materialTimePicker.addOnDismissListener {
            rootView.removeView(overlay)
        }

        materialTimePicker.addOnNegativeButtonClickListener {
            materialTimePicker.dismiss()
            rootView.removeView(overlay)
        }

        materialTimePicker.show(parentFragmentManager, "tag")
        overlay.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        overlay.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.half_trans))
        rootView.addView(overlay)

        overlay.setOnClickListener {
            materialTimePicker.dismiss()
            rootView.removeView(overlay)
        }
    }
    private fun bahanDibutuhkan() {
        val rootView = requireActivity().findViewById<ViewGroup>(android.R.id.content)
        val overlay = View(requireContext())
        overlay.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.half_trans))
        rootView.addView(overlay)

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_bahan_dibutuhkan, null)
        val editTextBahan = dialogView.findViewById<EditText>(R.id.edit_bahan)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Bahan Dibutuhkan")
            .setView(dialogView)
            .setOnDismissListener {
                rootView.removeView(overlay)
            }
            .setOnCancelListener {
                rootView.removeView(overlay)
            }
            .setPositiveButton("Simpan") { _, _ ->
                val text = editTextBahan.text?.toString()
                val supportingText: TextView = requireView().findViewById(R.id.st_bahan_dibutuhkan)
                supportingText.text = text
                rootView.removeView(overlay)
            }
            .setNegativeButton("Batal") { _, _ ->
                rootView.removeView(overlay)
            }
            .show()

    }
    private fun ingatkanSebelum() {
        val rootView = requireActivity().findViewById<ViewGroup>(android.R.id.content)
        val overlay = View(requireContext())
        overlay.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.half_trans))
        rootView.addView(overlay)

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_ingatkan, null)
        val editTextBahan = dialogView.findViewById<EditText>(R.id.ingatkan_sebelum)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Ingatkan sebelum")
            .setView(dialogView)
            .setOnDismissListener {
                rootView.removeView(overlay)
            }
            .setOnCancelListener {
                rootView.removeView(overlay)
            }
            .setPositiveButton("Simpan") { _, _ ->
                val text = editTextBahan.text?.toString()
                val supportingText: TextView = requireView().findViewById(R.id.st_ingatkan_sebelum)
                supportingText.text = text+"j sebelum"
                rootView.removeView(overlay)
            }
            .setNegativeButton("Batal") { _, _ ->
                rootView.removeView(overlay)
            }
            .show()

    }
    private fun tanggalAktivitas() {
        val rootView = requireActivity().findViewById<ViewGroup>(android.R.id.content)
        val overlay = View(requireContext())
        overlay.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.half_trans))
        rootView.addView(overlay)

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Pilih Tanggal Aktivitas")
            .setTheme(R.style.ThemeOverlay_App_DatePicker)
            .build()
        datePicker.addOnPositiveButtonClickListener {
            val selectedDateMillis = it
            val selectedDate = Date(selectedDateMillis)

            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate)
            val supportingText: TextView = requireView().findViewById(R.id.st_tanggal_aktivitas)
            supportingText.text = formattedDate
            rootView.removeView(overlay)
        }
        datePicker.addOnCancelListener {
            rootView.removeView(overlay)
        }
        datePicker.addOnDismissListener {
            rootView.removeView(overlay)
        }

        datePicker.addOnNegativeButtonClickListener {
            datePicker.dismiss()
            rootView.removeView(overlay)
        }

        datePicker.show(parentFragmentManager, "tag")

        overlay.setOnClickListener {
            datePicker.dismiss()
            rootView.removeView(overlay)
        }
    }

}



