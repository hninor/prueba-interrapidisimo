package com.hninor.pruebainterrapidisimo.features.localidades.data.network.dto

import com.hninor.pruebainterrapidisimo.features.localidades.domain.model.Localidad


data class LocalidadDTO(
    val AbreviacionCiudad: String,
    val Nombre: String,
    val NombreCompleto: String
)


fun LocalidadDTO.asDomain() = Localidad(
    abreviacion = AbreviacionCiudad,
    nombre = NombreCompleto
)

fun List<LocalidadDTO>.asDomain(): List<Localidad> {
    return map { it.asDomain() }
}