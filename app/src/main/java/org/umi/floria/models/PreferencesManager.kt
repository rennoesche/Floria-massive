package org.umi.floria.models

import android.content.Context
import android.content.SharedPreferences
import org.umi.floria.ui.ConfirmActivity
import org.umi.floria.ui.MainActivity
import org.umi.floria.ui.WelcomingActivity

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean("isLoggedIn", false)
        set(value) = sharedPreferences.edit().putBoolean("isLoggedIn", value).apply()

    var isFreshInstall: Boolean
        get() = sharedPreferences.getBoolean("FreshInstall", true)
        set(value) = sharedPreferences.edit().putBoolean("FreshInstall", value).apply()

    fun determineNextActivity(): Class<*> {
        val isLogin = isLoggedIn
        val isFreshInstall = isFreshInstall

        return when {
            isLogin && !isFreshInstall -> MainActivity::class.java
            !isLogin && !isFreshInstall -> ConfirmActivity::class.java
            else -> WelcomingActivity::class.java
        }
    }
    fun getUserId(): Long {
        return sharedPreferences.getLong("user_id", -1)
    }

    fun setUserId(userId: Long) {
        sharedPreferences.edit().putLong("user_id", userId).apply()
    }
}
