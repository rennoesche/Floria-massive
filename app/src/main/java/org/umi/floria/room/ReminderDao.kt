package org.umi.floria.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders")
    suspend fun getAllReminders(): List<Reminder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminders: Reminder)

    @Update
    suspend fun updateReminder(reminders: Reminder)

    @Delete
    suspend fun deleteReminder(reminders: Reminder)

}