package com.hninor.pruebainterrapidisimo.features.splash.data.network

import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.LocalidadesApi
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.dto.LocalidadDTO
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.TableApi
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.dto.TableDTO
import kotlinx.coroutines.delay

class VersionRemoteDataSource(private val versionApi: VersionApi) {

    suspend fun getVersion(): String {
        try {
            return versionApi.getVersion()
        } catch (e: Exception) {
            throw ApiException("Failed to get tables", e)
        }

    }
}

interface VersionApi {
    suspend fun getVersion(): String
}