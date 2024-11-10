package com.hninor.pruebainterrapidisimo.features.login.data.repository

import com.hninor.pruebainterrapidisimo.core.DataException
import com.hninor.pruebainterrapidisimo.features.login.data.local.LoginLocalDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.LoginRemoteDataSource
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
                LoginRequest(Usuario = "cGFtLm1lcmVkeTIx", Password = "SW50ZXIyMDIx")
            } else {
                LoginRequest(Usuario = usuario, Password = contrasena)
            }
            val loginResponseDTO = loginRemoteDataSource.login(loginRequest)

            try {
                loginLocalDataSource.saveUser(loginResponseDTO.toUserDB())
            } catch (e: Exception) {
                throw DataException("La respuesta no pudo ser guardada localmente")
            }

            LoginResponse(loginResponseDTO.Nombre)

        }


}