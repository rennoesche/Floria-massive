package org.umi.floria.models

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.umi.floria.room.AppDatabase
import org.umi.floria.room.Reminder

class DataReminder(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val reminderDao = database.reminderDao()

    fun getReminderList(callback: (List<Reminder>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            callback(reminderDao.getAllReminders())
        }
    }
    fun addReminder(reminder: Reminder) {
        CoroutineScope(Dispatchers.Main).launch {
            reminderDao.insertReminder(reminder)
        }
    }
    fun deleteReminder(reminder: Reminder) {
        CoroutineScope(Dispatchers.Main).launch {
            reminderDao.deleteReminder(reminder)
        }
    }
    fun updateReminder(reminder: Reminder) {
        CoroutineScope(Dispatchers.Main).launch {
            reminderDao.updateReminder(reminder)
        }
    }
}