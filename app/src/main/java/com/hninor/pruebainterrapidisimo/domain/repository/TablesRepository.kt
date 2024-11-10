package com.hninor.pruebainterrapidisimo.domain.repository

import com.hninor.pruebainterrapidisimo.domain.model.Table

interface TablesRepository {

    suspend fun getTables(usuario: String, identificacion: String): List<Table>
}