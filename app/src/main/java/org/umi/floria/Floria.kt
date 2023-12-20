package org.umi.floria

import android.app.Application
import android.util.Log
import org.umi.floria.room.AppDatabase

class Floria : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inisialisasi RoomDatabase atau ViewModel dengan database Room di sini
        // Contoh inisialisasi: AppDatabase.getInstance(this)
        AppDatabase.getInstance(this)

        Log.d("RoomDatabase", "Database Room telah dibuat")
    }
}
