package com.hninor.pruebainterrapidisimo.features.splash.data.repository

import com.hninor.pruebainterrapidisimo.features.splash.data.local.VersionLocalDataSource
import com.hninor.pruebainterrapidisimo.features.splash.data.network.VersionRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.splash.domain.repository.VersionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VersionDataRepository(
    private val versionLocalDataSource: VersionLocalDataSource,
    private val versionRemoteDataSource: VersionRemoteDataSource,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : VersionRepository {
    override suspend fun getVersion(fromRemote: Boolean): Int =
        withContext(backgroundDispatcher) {
            if (fromRemote) {
                val version = versionRemoteDataSource.getVersion()
                versionLocalDataSource.saveVersion(version)
                version.toInt()
            } else {
                versionLocalDataSource.getVersion().toInt()
            }
        }

    override suspend fun getLocalVersionName(): String = withContext(backgroundDispatcher) {
        versionLocalDataSource.getLocalVersionName()
    }

    override suspend fun getLocalVersion(): Int = withContext(backgroundDispatcher) {
        versionLocalDataSource.getLocalVersion()
    }


}