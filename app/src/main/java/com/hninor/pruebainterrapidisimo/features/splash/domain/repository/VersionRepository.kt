package com.hninor.pruebainterrapidisimo.features.splash.domain.repository

interface VersionRepository {
    suspend fun getVersion(fromRemote: Boolean): Int

    suspend fun getLocalVersionName(): String
    suspend fun getLocalVersion(): Int

}