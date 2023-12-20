package org.umi.floria.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.umi.floria.models.LoginCallback
import org.umi.floria.models.PreferencesManager
import org.umi.floria.room.AppDatabase
import org.umi.floria.room.User
import org.umi.floria.room.UserDao

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao
    private val preferencesManager: PreferencesManager

    var loginCallback: LoginCallback? = null
    init {
        val db = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "my-database"
        ).build()

        userDao = db.userDao()
        preferencesManager = PreferencesManager(application)
    }

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(name = name, email = email, password = password)
            userDao.insertUser(user)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.login(email, password)
            if (user != null) {
                preferencesManager.setUserId(user.id)
                preferencesManager.isLoggedIn = true
                loginCallback?.onLoginSuccess()
            } else {
                loginCallback?.onLoginFailed()
            }
        }
    }
}

