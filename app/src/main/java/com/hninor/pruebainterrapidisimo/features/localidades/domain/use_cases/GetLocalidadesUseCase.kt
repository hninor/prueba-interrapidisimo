package com.hninor.pruebainterrapidisimo.features.localidades.domain.use_cases

import com.hninor.pruebainterrapidisimo.features.tablas.domain.model.Table
import com.hninor.pruebainterrapidisimo.features.tablas.domain.repository.TablesRepository

class GetLocalidadesUseCase(private val tablesRepository: TablesRepository) {
    suspend operator fun invoke(usuario: String, identificacion: String): List<Table> {
        return tablesRepository.getTables(usuario, identificacion)
    }
}