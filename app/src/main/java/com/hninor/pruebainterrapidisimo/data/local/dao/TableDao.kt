package com.hninor.pruebainterrapidisimo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hninor.pruebainterrapidisimo.data.local.dto.TableDTO

@Dao
interface TableDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTable(tableDTO: TableDTO)

    @Query("SELECT * FROM tablas WHERE usuario = :usuario AND identificacion = :identificacion")
    suspend fun getTablesByUsuario(usuario: String, identificacion: String): List<TableDTO>

    @Query("DELETE FROM tablas WHERE usuario = :usuario AND identificacion = :identificacion")
    suspend fun deleteTablesByUsuario(usuario: String, identificacion: String)


    @Delete
    suspend fun delete(tableDTO: TableDTO)
}