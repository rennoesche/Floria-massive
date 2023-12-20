package org.umi.floria.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.umi.floria.models.DataReminder
import org.umi.floria.room.AppDatabase
import org.umi.floria.room.Reminder

class ReminderViewModel(application: Application): AndroidViewModel(application) {
    private val appDatabase = AppDatabase.getInstance(application)
    val reminder = MutableLiveData<List<Reminder>>()

    fun getReminderList(context: Context) {
        val dataReminder = DataReminder(context)
        dataReminder.getReminderList { reminders ->
            reminder.postValue(reminders)
        }
    }
    fun insertReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.reminderDao().insertReminder(reminder)
        }
    }
}