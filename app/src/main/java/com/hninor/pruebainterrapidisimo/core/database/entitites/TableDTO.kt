package com.hninor.pruebainterrapidisimo.core.database.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hninor.pruebainterrapidisimo.features.tablas.domain.model.Table


@Entity(tableName = "tablas")
data class TableDTO (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val query: String,
    val numeroCampos: Int,
    val usuario: String,
    val identificacion: String
)

fun TableDTO.asDomain() = Table(
    nombre = nombre,
    queryCreacion = query,
    numeroCampos = numeroCampos
)

fun List<TableDTO>.asDomain(): List<Table> {
    return map { it.asDomain() }
}


