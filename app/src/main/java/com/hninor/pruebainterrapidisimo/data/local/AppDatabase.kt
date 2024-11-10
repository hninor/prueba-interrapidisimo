package com.hninor.pruebainterrapidisimo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hninor.pruebainterrapidisimo.data.local.dao.TableDao
import com.hninor.pruebainterrapidisimo.data.local.dto.TableDTO

@Database(entities = [TableDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tableDao(): TableDao
}