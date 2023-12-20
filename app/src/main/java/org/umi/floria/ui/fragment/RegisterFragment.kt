package org.umi.floria.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import org.umi.floria.R
import org.umi.floria.vm.LoginViewModel

class RegisterFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val textName = view.findViewById<TextInputLayout>(R.id.layout_it_name_register).editText
        val editTextEmail = view.findViewById<TextInputLayout>(R.id.layout_it_email_register).editText
        val editTextPassword = view.findViewById<TextInputLayout>(R.id.layout_it_password_register).editText
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val textLogin = view.findViewById<TextView>(R.id.text_login_signup)

        btnRegister.setOnClickListener {
            val name = textName?.text.toString().trim()
            val email = editTextEmail?.text.toString().trim()
            val password = editTextPassword?.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registerUser(name, email, password)

                MaterialAlertDialogBuilder(requireContext())
                    .setView(R.layout.dialog_register_sukses)
                    .setOnCancelListener {
                        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

                        transaction.replace(android.R.id.content, LoginFragment())
                        transaction.commit()
                    }
                    .setOnDismissListener {
                        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

                        transaction.replace(android.R.id.content, LoginFragment())
                        transaction.commit()
                    }
                    .show()
            }
        }
        textLogin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(android.R.id.content, LoginFragment()).commit()
        }
    }
}
