package org.umi.floria.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.umi.floria.R
import org.umi.floria.models.PreferencesManager
import org.umi.floria.room.AppDatabase
import org.umi.floria.ui.ConfirmActivity

class ProfilFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = view.findViewById<TextView>(R.id.user_name)

        val preferencesManager = PreferencesManager(requireContext())
        val userId = preferencesManager.getUserId()

        lifecycleScope.launch(Dispatchers.IO) {
            val userDao = AppDatabase.getInstance(requireContext()).userDao()
            val text = userDao.getUserById(userId)

            withContext(Dispatchers.Main) {
                userName.text = "$text"
            }
        }
        sharedPreferences = requireActivity().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val btnLogout = view.findViewById<TextView>(R.id.logout_cok)
        btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage("Anda ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    logout()
                }
                .setNegativeButton("Tidak") { _, _ ->
                }
                .setOnDismissListener {}
                .setOnCancelListener {}
                .show()
        }
    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn" , false)
        editor.apply()

        keLogin()
    }

    private fun keLogin() {
        val intent = Intent(requireContext(), ConfirmActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}

