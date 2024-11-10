package com.hninor.pruebainterrapidisimo.domain.use_cases

import com.hninor.pruebainterrapidisimo.domain.model.Table
import com.hninor.pruebainterrapidisimo.domain.repository.TablesRepository

class GetTablesUseCase(private val tablesRepository: TablesRepository) {
    suspend operator fun invoke(usuario: String, identificacion: String): List<Table> {
        return tablesRepository.getTables(usuario, identificacion)
    }
}