package com.hninor.pruebainterrapidisimo.features.login.domain.use_cases

import com.hninor.pruebainterrapidisimo.features.login.domain.repository.LoginRepository

class LoginUserUseCase(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(usuario: String, contrasena: String) =
        loginRepository.login(usuario, contrasena)
}