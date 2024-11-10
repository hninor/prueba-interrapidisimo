package com.hninor.pruebainterrapidisimo.features.login.data.network.api

import com.hninor.pruebainterrapidisimo.features.localidades.data.network.dto.LocalidadDTO
import com.hninor.pruebainterrapidisimo.features.login.data.network.LoginApi
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginRequest
import com.hninor.pruebainterrapidisimo.features.login.data.network.dto.LoginResponseDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://apitesting.interrapidisimo.co/FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/"

interface LoginRetrofitApi : LoginApi {

    @POST("Seguridad/AuthenticaUsuarioApp")
    override suspend fun login(@Body request: LoginRequest): Response<LoginResponseDTO>
}

fun provideLoginRetrofitApi(): LoginRetrofitApi {

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit.create(LoginRetrofitApi::class.java)
}