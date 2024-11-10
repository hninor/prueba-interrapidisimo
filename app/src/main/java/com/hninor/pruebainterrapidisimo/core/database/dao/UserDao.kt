package com.hninor.pruebainterrapidisimo.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hninor.pruebainterrapidisimo.core.database.entitites.UserDB

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDB: UserDB)

    @Query("DELETE FROM usuario")
    fun deleteAll()
}