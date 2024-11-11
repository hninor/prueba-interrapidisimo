package com.hninor.pruebainterrapidisimo.features.splash.domain.use_cases

import com.hninor.pruebainterrapidisimo.features.splash.domain.repository.VersionRepository

class GetVersionUseCase(private val versionRepository: VersionRepository) {
    suspend operator fun invoke(fromRemote: Boolean) = versionRepository.getVersion(fromRemote)
    suspend fun getLocalVersionName() = versionRepository.getLocalVersionName()
    suspend fun getLocalVersion() = versionRepository.getLocalVersion()
}