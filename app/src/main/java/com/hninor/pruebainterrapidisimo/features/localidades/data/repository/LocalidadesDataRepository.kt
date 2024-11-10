package com.hninor.pruebainterrapidisimo.features.localidades.data.repository

import com.hninor.pruebainterrapidisimo.features.localidades.data.network.LocalidadesRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.dto.asDomain
import com.hninor.pruebainterrapidisimo.features.localidades.domain.model.Localidad
import com.hninor.pruebainterrapidisimo.features.localidades.domain.repository.LocalidadesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalidadesDataRepository(
    private val localidadesRemoteDataSource: LocalidadesRemoteDataSource,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalidadesRepository {


    override suspend fun getLocalidades(): List<Localidad> = withContext(backgroundDispatcher) {
        localidadesRemoteDataSource.getLocalidades().asDomain()
    }


}