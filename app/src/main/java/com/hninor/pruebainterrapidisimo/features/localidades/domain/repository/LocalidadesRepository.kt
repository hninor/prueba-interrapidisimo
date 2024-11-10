package com.hninor.pruebainterrapidisimo.features.localidades.domain.repository

import com.hninor.pruebainterrapidisimo.features.localidades.domain.model.Localidad

interface LocalidadesRepository {

    suspend fun getLocalidades(): List<Localidad>
}