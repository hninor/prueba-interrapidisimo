package com.hninor.pruebainterrapidisimo.data.network

import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.data.network.dto.TableDTO

class TablesRemoteDataSource(private val tableApi: TableApi) {

    suspend fun getTables(usuario: String, identificacion: String): List<TableDTO> {
        try {
            return tableApi.getTables(usuario, identificacion)
        } catch (e: Exception) {
            throw ApiException("Failed to get tables", e)
        }

    }
}

interface TableApi {
    suspend fun getTables(usuario: String, identificacion: String): List<TableDTO>
}