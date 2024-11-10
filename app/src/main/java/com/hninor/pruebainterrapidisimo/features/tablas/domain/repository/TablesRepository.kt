package com.hninor.pruebainterrapidisimo.features.tablas.domain.repository

import com.hninor.pruebainterrapidisimo.features.tablas.domain.model.Table

interface TablesRepository {

    suspend fun getTables(usuario: String, identificacion: String): List<Table>
}