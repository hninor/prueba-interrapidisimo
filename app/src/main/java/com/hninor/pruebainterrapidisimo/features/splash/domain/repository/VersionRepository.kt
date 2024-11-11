package com.hninor.pruebainterrapidisimo.features.splash.domain.repository

interface VersionRepository {
    suspend fun getVersion(fromRemote: Boolean): String

}