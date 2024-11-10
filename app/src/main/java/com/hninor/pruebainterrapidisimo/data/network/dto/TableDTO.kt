package com.hninor.pruebainterrapidisimo.data.network.dto

import com.hninor.pruebainterrapidisimo.domain.model.Table


data class TableDTO(val NombreTabla: String, val QueryCreacion: String, val NumeroCampos: Int)


fun TableDTO.asDomain() = Table(
    nombre = NombreTabla,
    queryCreacion = QueryCreacion,
    numeroCampos = NumeroCampos
)

fun List<TableDTO>.asDomain(): List<Table> {
    return map { it.asDomain() }
}