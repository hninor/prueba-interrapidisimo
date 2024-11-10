package com.hninor.pruebainterrapidisimo.data.repository

import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.data.local.TablesLocalDataSource
import com.hninor.pruebainterrapidisimo.data.local.dto.asDomain
import com.hninor.pruebainterrapidisimo.data.network.TablesRemoteDataSource
import com.hninor.pruebainterrapidisimo.data.network.dto.asDomain
import com.hninor.pruebainterrapidisimo.domain.model.Table
import com.hninor.pruebainterrapidisimo.domain.repository.TablesRepository
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