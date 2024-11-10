package com.hninor.pruebainterrapidisimo.features.localidades.domain.use_cases

import com.hninor.pruebainterrapidisimo.features.localidades.domain.model.Localidad
import com.hninor.pruebainterrapidisimo.features.localidades.domain.repository.LocalidadesRepository

class GetLocalidadesUseCase(private val localidadesRepository: LocalidadesRepository) {
    suspend operator fun invoke(): List<Localidad> {
        return localidadesRepository.getLocalidades()
    }
}