package org.umi.floria.vm

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.umi.floria.models.PreferencesManager
import org.umi.floria.room.*
import org.umi.floria.ui.fragment.LoginFragment

class AuthViewModel(application: LoginFragment) : AndroidViewModel(application) {
    private val userDao: UserDao
    private val preferencesManager: PreferencesManager

    init {
        val db = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "my-database"
        ).build()

        userDao = db.userDao()
        preferencesManager = PreferencesManager(application)
    }

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.login(email, password)
            if (user != null) {
                preferencesManager.isLoggedIn = true
            } else {
                // Handle failed login
            }
        }
    }
}
