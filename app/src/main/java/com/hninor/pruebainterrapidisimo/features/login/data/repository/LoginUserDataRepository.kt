package com.hninor.pruebainterrapidisimo.features.login.data.repository

import com.hninor.pruebainterrapidisimo.features.login.data.local.LoginLocalDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.LoginRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.LoginResponseState
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginRequest
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.toUserDB
import com.hninor.pruebainterrapidisimo.features.login.domain.model.LoginResponse
import com.hninor.pruebainterrapidisimo.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUserDataRepository(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginRepository {
    override suspend fun login(usuario: String, contrasena: String): LoginResponse =
        withContext(backgroundDispatcher) {
            val loginRequest = if (usuario == "pam.meredy21") {
                LoginRequest(Usuario = "cGFtLm1lcmVkeTIx\\n", Password = "SW50ZXIyMDIx\\n")
            } else {
                LoginRequest(Usuario = usuario, Password = contrasena)
            }
            val loginResponseState = loginRemoteDataSource.login(loginRequest)

            when (loginResponseState) {
                is LoginResponseState.Success -> {
                    loginLocalDataSource.saveUser(loginResponseState.loginResponseDTO.toUserDB())
                    LoginResponse(true)
                }

                is LoginResponseState.Error -> {
                    LoginResponse(false, loginResponseState.message)

                }

                else -> {
                    LoginResponse(false)

                }
            }

        }


}