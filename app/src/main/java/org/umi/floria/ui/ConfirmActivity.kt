package org.umi.floria.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.umi.floria.R
import org.umi.floria.ui.fragment.LoginFragment
import org.umi.floria.ui.fragment.RegisterFragment

class ConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val confirmRegister = findViewById<Button>(R.id.confirm_signup)
        val confirmLogin = findViewById<Button>(R.id.confirm_signin)

        confirmLogin.setOnClickListener {
            replaceFragment(LoginFragment())
        }

        confirmRegister.setOnClickListener {
            replaceFragment(RegisterFragment())
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(android.R.id.content, fragment)

        transaction.commit()
    }
}
