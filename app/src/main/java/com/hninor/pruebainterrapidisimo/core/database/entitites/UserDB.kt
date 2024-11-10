package com.hninor.pruebainterrapidisimo.core.database.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hninor.pruebainterrapidisimo.features.tablas.domain.model.Table


@Entity(tableName = "usuario")
data class UserDB (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val usuario: String,
    val identificacion: String,
    val nombre: String
)




