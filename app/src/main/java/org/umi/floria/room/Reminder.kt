package org.umi.floria.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder constructor(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val plantName: String,
    val activityTime: Int,
    val requiredMaterials: String,
    val reminderBeforeHours: Int,
    val activityDate: Long,
    val activityCreated: Long,
    val description: String
)
