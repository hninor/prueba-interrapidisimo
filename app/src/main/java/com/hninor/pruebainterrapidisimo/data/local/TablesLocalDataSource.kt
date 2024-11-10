package com.hninor.pruebainterrapidisimo.data.local

import com.hninor.pruebainterrapidisimo.data.local.dao.TableDao
import com.hninor.pruebainterrapidisimo.data.network.dto.TableDTO

class TablesLocalDataSource(private val tableDao: TableDao) {


    suspend fun insertTables(usuario: String, identificacion: String, tables: List<TableDTO>) {
        tableDao.deleteTablesByUsuario(usuario, identificacion)
        tables.forEach {
            tableDao.insertTable(
                com.hninor.pruebainterrapidisimo.data.local.dto.TableDTO(
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
    ): List<com.hninor.pruebainterrapidisimo.data.local.dto.TableDTO> {
        return tableDao.getTablesByUsuario(usuario, identificacion)
    }
}