package com.hninor.pruebainterrapidisimo.features.login.data.network

import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginRequest
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginResponseDTO
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException


class LoginRemoteDataSource(private val loginApi: LoginApi) {

    suspend fun login(request: LoginRequest): LoginResponseDTO {
        try {
            val response = loginApi.login(request)
            return if (response.isSuccessful) {
                val headers = response.headers()
                response.body() ?: throw ApiException("No body in response", Exception())

            } else {
                val code = response.code()
                throw ApiException("Http response: $code", Exception())
            }
        } catch (e: HttpException) {
            val response = e.response()
            val errorCode = e.code()
            throw ApiException("Http response: $errorCode, ${response?.errorBody().toString()}", e)
        } catch (e: UnknownHostException) {
            // Either the server is down or the device doesn't have internet access
            throw ApiException("Revise su conexión a Internet", e)
        }
    }


}

interface LoginApi {
    suspend fun login(body: LoginRequest): Response<LoginResponseDTO>
}