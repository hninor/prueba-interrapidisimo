package com.hninor.pruebainterrapidisimo.features.splash.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hninor.pruebainterrapidisimo.core.InterrapidisimoApplication
import com.hninor.pruebainterrapidisimo.features.splash.data.local.VersionLocalDataSource
import com.hninor.pruebainterrapidisimo.features.splash.data.network.VersionRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.splash.data.network.api.provideVersionRetrofitApi
import com.hninor.pruebainterrapidisimo.features.splash.data.repository.VersionDataRepository
import com.hninor.pruebainterrapidisimo.features.splash.domain.use_cases.GetVersionUseCase
import kotlinx.coroutines.launch


class SplashViewModel(private val getVersionUseCase: GetVersionUseCase) : ViewModel() {



    init {

        getVersion()


    }

    fun getVersion() {
        viewModelScope.launch {
            try {
                val version = getVersionUseCase(fromRemote = true)
                Log.d("SplashScreen", "Version: $version")
            } catch (e: Exception) {
                Log.d("SplashScreen", "Falló la consulta de versión")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SplashViewModel(
                    GetVersionUseCase(
                        VersionDataRepository(
                            versionLocalDataSource = VersionLocalDataSource(
                                InterrapidisimoApplication.appContext
                            ),
                            versionRemoteDataSource = VersionRemoteDataSource(
                                provideVersionRetrofitApi()
                            )
                        )
                    )
                )
            }
        }
    }

}