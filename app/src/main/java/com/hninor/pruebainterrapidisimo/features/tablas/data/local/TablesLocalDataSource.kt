package com.hninor.pruebainterrapidisimo.features.tablas.data.local

import android.content.Context
import com.hninor.pruebainterrapidisimo.core.database.dao.TableDao
import com.hninor.pruebainterrapidisimo.core.database.entitites.TableDTO
import com.hninor.pruebainterrapidisimo.core.database.sqlitehelper.DBHelper

class TablesLocalDataSource(private val tableDao: TableDao, private val context: Context) {


    suspend fun insertTables(
        usuario: String,
        identificacion: String,
        tables: List<com.hninor.pruebainterrapidisimo.features.tablas.data.network.dto.TableDTO>
    ) {
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

    suspend fun createDatabase(tables: List<com.hninor.pruebainterrapidisimo.features.tablas.data.network.dto.TableDTO>) {
        val queryList = tables.map { it.QueryCreacion }
        val dbHelper = DBHelper(context, queryList, null)

        dbHelper.writableDatabase
    }
}