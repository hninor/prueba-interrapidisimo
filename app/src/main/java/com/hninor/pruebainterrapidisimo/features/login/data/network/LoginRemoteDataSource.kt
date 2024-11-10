package com.hninor.pruebainterrapidisimo.features.login.data.network

import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginRequest
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginResponseDTO
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

sealed interface LoginResponseState {
    data class Success(val loginResponseDTO: LoginResponseDTO) : LoginResponseState
    data class Error(val message: String) : LoginResponseState

}
class LoginRemoteDataSource(private val loginApi: LoginApi) : LoginResponseState {

    suspend fun login(request: LoginRequest): LoginResponseState {
        try {
            val response = loginApi.login(request)
            return if (response.isSuccessful) {
                val headers = response.headers()
                val body = response.body()
                if (body != null) {
                    LoginResponseState.Success(body)
                } else {
                    LoginResponseState.Error("Body null")
                }

            } else {
                val code = response.code()
                LoginResponseState.Error("Http response: $code")
            }
        } catch (e: HttpException) {
            val response = e.response()
            val errorCode = e.code()
            return LoginResponseState.Error("Http response: $errorCode, ${response?.errorBody().toString()}")
        } catch (e: UnknownHostException) {
            // Either the server is down or the device doesn't have internet access
            return LoginResponseState.Error("Revise su conexión a Internet")
        }
    }


}

interface LoginApi {
    suspend fun login(body: LoginRequest): Response<LoginResponseDTO>
}