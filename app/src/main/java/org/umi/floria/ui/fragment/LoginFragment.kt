package org.umi.floria.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import org.umi.floria.R
import org.umi.floria.models.LoginCallback
import org.umi.floria.ui.MainActivity
import org.umi.floria.vm.LoginViewModel

class LoginFragment : Fragment(), LoginCallback {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.loginCallback = this

        val editTextEmail = view.findViewById<TextInputLayout>(R.id.layout_it_email).editText
        val editTextPassword = view.findViewById<TextInputLayout>(R.id.layout_it_password).editText
        val btnLogin = view.findViewById<Button>(R.id.btnSignin)
        val textRegist = view.findViewById<TextView>(R.id.text_login_signup)

        btnLogin.setOnClickListener {
            val email = editTextEmail?.text.toString().trim()
            val password = editTextPassword?.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(email, password)
            }
        }
        textRegist.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(android.R.id.content, RegisterFragment()).commit()
        }
    }
    override fun onLoginSuccess() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onLoginFailed() {
        // Tampilkan pesan kesalahan login
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(requireContext(), "Login failed. Invalid email or password.", Toast.LENGTH_SHORT).show()
        }    }
}