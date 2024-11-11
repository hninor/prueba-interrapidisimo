package com.hninor.pruebainterrapidisimo.features.splash.domain.use_cases

enum class CompareResult {
    GREATER,
    EQUAL,
    LESS_THAN
}
class CompareVersionsUseCase(private val getVersionUseCase: GetVersionUseCase) {

    suspend operator fun invoke(): CompareResult {
        val versionLocal = getVersionUseCase.getLocalVersion()
        val version = getVersionUseCase(false)

        return if (versionLocal < version) {
            CompareResult.LESS_THAN
        } else if (versionLocal > version) {
            CompareResult.GREATER
        } else {
            CompareResult.EQUAL
        }
    }

}