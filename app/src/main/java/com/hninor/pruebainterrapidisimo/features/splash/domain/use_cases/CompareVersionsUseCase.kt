package com.hninor.pruebainterrapidisimo.features.splash.domain.use_cases

class CompareVersionsUseCase(private val getVersionUseCase: GetVersionUseCase) {

    suspend operator fun invoke(): Boolean {
        val versionLocal = getVersionUseCase.getLocalVersion()
        val version = getVersionUseCase(false)
        return versionLocal < version
    }


}