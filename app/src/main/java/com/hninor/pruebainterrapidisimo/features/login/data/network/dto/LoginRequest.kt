package com.hninor.pruebainterrapidisimo.features.login.data.network.dto


data class LoginRequest(
    val Usuario: String,
    val Password: String,
    val Path: String = "",
    val Mac: String = "",
    val NomAplicacion: String = "Controller APP"

)


