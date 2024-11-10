package com.hninor.pruebainterrapidisimo.features.tablas.data.repository

import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.features.tablas.data.local.TablesLocalDataSource
import com.hninor.pruebainterrapidisimo.core.database.entitites.asDomain
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.TablesRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.dto.asDomain
import com.hninor.pruebainterrapidisimo.features.tablas.domain.model.Table
import com.hninor.pruebainterrapidisimo.features.tablas.domain.repository.TablesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TablesDataRepository(
    private val tablesRemoteDataSource: TablesRemoteDataSource,
    private val tablesLocalDataSource: TablesLocalDataSource,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TablesRepository {

    override suspend fun getTables(usuario: String, identificacion: String): List<Table> =
        withContext(backgroundDispatcher) {
            try {
                val tables = tablesRemoteDataSource.getTables(usuario, identificacion)
                tablesLocalDataSource.insertTables(usuario, identificacion, tables)
                tables.asDomain()
            } catch (e: ApiException) {
                val tables = tablesLocalDataSource.getTables(usuario, identificacion)
                if (tables.isEmpty()) {
                    throw Exception("Failed to get tables")
                } else {
                    tables.asDomain()
                }

            }

        }


}