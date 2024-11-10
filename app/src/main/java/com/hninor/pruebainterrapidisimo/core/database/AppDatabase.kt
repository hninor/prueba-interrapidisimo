package com.hninor.pruebainterrapidisimo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hninor.pruebainterrapidisimo.core.database.dao.TableDao
import com.hninor.pruebainterrapidisimo.core.database.dao.UserDao
import com.hninor.pruebainterrapidisimo.core.database.entitites.TableDTO
import com.hninor.pruebainterrapidisimo.core.database.entitites.UserDB

@Database(entities = [TableDTO::class, UserDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tableDao(): TableDao
    abstract fun userDao(): UserDao
}