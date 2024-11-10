package com.hninor.pruebainterrapidisimo.features.login.domain.repository

import com.hninor.pruebainterrapidisimo.features.login.domain.model.LoginResponse

interface LoginRepository {

    suspend fun login(usuario: String, contrasena: String): LoginResponse
}