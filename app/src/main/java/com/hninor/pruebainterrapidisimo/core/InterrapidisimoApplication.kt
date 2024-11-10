package com.hninor.pruebainterrapidisimo.core

import android.app.Application
import androidx.room.Room
import com.hninor.pruebainterrapidisimo.data.local.AppDatabase

class InterrapidisimoApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        ).build()
    }
}