package com.hninor.pruebainterrapidisimo.data.network.api

import com.hninor.pruebainterrapidisimo.data.network.TableApi
import com.hninor.pruebainterrapidisimo.data.network.dto.TableDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

private const val BASE_URL = "https://apitesting.interrapidisimo.co/apicontrollerpruebas/api/"

interface TableRetrofitApi : TableApi {

    @GET("SincronizadorDatos/ObtenerEsquema/true")
    override suspend fun getTables(
        @Header("Usuario") usuario: String,
        @Header("Identificacion") identificacion: String
    ): List<TableDTO>
}

fun provideTableRetrofitApi(): TableRetrofitApi {

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit.create(TableRetrofitApi::class.java)
}