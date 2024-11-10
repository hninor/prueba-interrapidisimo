package com.hninor.pruebainterrapidisimo.features.localidades.data.network

import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.dto.LocalidadDTO
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.TableApi
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.dto.TableDTO

class LocalidadesRemoteDataSource(private val localidadesApi: LocalidadesApi) {

    suspend fun getLocalidades(): List<LocalidadDTO> {
        try {
            return localidadesApi.getLocalidades()
        } catch (e: Exception) {
            throw ApiException("Failed to get tables", e)
        }

    }
}

interface LocalidadesApi {
    suspend fun getLocalidades(): List<LocalidadDTO>
}