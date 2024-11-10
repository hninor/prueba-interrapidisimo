package com.hninor.pruebainterrapidisimo.features.localidades.data.network.api

import com.hninor.pruebainterrapidisimo.features.localidades.data.network.LocalidadesApi
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.dto.LocalidadDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://apitesting.interrapidisimo.co/apicontrollerpruebas/api/"

interface LocalidadRetrofitApi : LocalidadesApi {

    @GET("ParametrosFramework/ObtenerLocalidadesRecogidas")
    override suspend fun getLocalidades(): List<LocalidadDTO>
}

fun provideLocalidadRetrofitApi(): LocalidadRetrofitApi {

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit.create(LocalidadRetrofitApi::class.java)
}