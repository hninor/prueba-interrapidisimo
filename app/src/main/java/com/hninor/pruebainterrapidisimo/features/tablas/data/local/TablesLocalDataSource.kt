package com.hninor.pruebainterrapidisimo.features.tablas.data.local

import com.hninor.pruebainterrapidisimo.features.tablas.data.local.dao.TableDao
import com.hninor.pruebainterrapidisimo.features.tablas.data.local.dto.TableDTO

class TablesLocalDataSource(private val tableDao: TableDao) {


    suspend fun insertTables(usuario: String, identificacion: String, tables: List<com.hninor.pruebainterrapidisimo.features.tablas.data.network.dto.TableDTO>) {
        tableDao.deleteTablesByUsuario(usuario, identificacion)
        tables.forEach {
            tableDao.insertTable(
                TableDTO(
                    nombre = it.NombreTabla,
                    query = it.QueryCreacion,
                    numeroCampos = it.NumeroCampos,
                    usuario = usuario,
                    identificacion = identificacion
                )
            )
        }

    }

    suspend fun getTables(
        usuario: String,
        identificacion: String
    ): List<TableDTO> {
        return tableDao.getTablesByUsuario(usuario, identificacion)
    }
}