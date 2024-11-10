package com.hninor.pruebainterrapidisimo.features.login.data.network.dto

import com.hninor.pruebainterrapidisimo.core.database.entitites.UserDB


data class LoginResponseDTO(
    val Usuario: String,
    val Identificacion: String,
    val Nombre: String
)

fun LoginResponseDTO.toUserDB() =
    UserDB(usuario = Usuario, identificacion = Identificacion, nombre = Nombre)


