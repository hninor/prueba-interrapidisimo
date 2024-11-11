package com.hninor.pruebainterrapidisimo.features.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hninor.pruebainterrapidisimo.R
import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.core.DataException
import com.hninor.pruebainterrapidisimo.core.InterrapidisimoApplication
import com.hninor.pruebainterrapidisimo.features.login.data.local.LoginLocalDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.LoginRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.api.provideLoginRetrofitApi
import com.hninor.pruebainterrapidisimo.features.login.data.repository.LoginUserDataRepository
import com.hninor.pruebainterrapidisimo.features.login.domain.model.LoginResponse
import com.hninor.pruebainterrapidisimo.features.login.domain.use_cases.LoginUserUseCase
import com.hninor.pruebainterrapidisimo.features.splash.data.local.VersionLocalDataSource
import com.hninor.pruebainterrapidisimo.features.splash.data.network.VersionRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.splash.data.network.api.provideVersionRetrofitApi
import com.hninor.pruebainterrapidisimo.features.splash.data.repository.VersionDataRepository
import com.hninor.pruebainterrapidisimo.features.splash.domain.use_cases.CompareVersionsUseCase
import com.hninor.pruebainterrapidisimo.features.splash.domain.use_cases.GetVersionUseCase
import kotlinx.coroutines.launch


sealed interface LoginUiState {
    data class Success(val loginResponse: LoginResponse) : LoginUiState
    data class Error(val message: String) : LoginUiState
    object Loading : LoginUiState
    object Home : LoginUiState
}

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val getVersionUseCase: GetVersionUseCase,
    private val compareVersionsUseCase: CompareVersionsUseCase
) :
    ViewModel() {

    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Home)
        private set

    var version: String by mutableStateOf("")
        private set

    init {
        getVersion()
        compareVersions()
    }

    private fun compareVersions() {
        viewModelScope.launch {
            if (compareVersionsUseCase()) {
                val ultimaVersion = getVersionUseCase(false).toString()
                loginUiState = LoginUiState.Error(
                    InterrapidisimoApplication.appContext.getString(
                        R.string.update_version,
                        ultimaVersion
                    )
                )
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUiState = LoginUiState.Loading

            loginUiState = try {
                LoginUiState.Success(loginUserUseCase(username, password))
            } catch (e: ApiException) {
                LoginUiState.Error(e.customMessage)
            } catch (e: DataException) {
                LoginUiState.Error(e.customMessage)
            }


        }
    }

    fun onConfirmation() {
        loginUiState = LoginUiState.Home
    }

    fun getVersion() {
        viewModelScope.launch {
            version = getVersionUseCase.getLocalVersionName()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                val getVersionsUseCase = GetVersionUseCase(
                    VersionDataRepository(
                        versionRemoteDataSource = VersionRemoteDataSource(
                            provideVersionRetrofitApi()
                        ),
                        versionLocalDataSource = VersionLocalDataSource(
                            InterrapidisimoApplication.appContext
                        )
                    )
                )

                LoginViewModel(
                    loginUserUseCase = LoginUserUseCase(
                        LoginUserDataRepository(
                            LoginRemoteDataSource(
                                provideLoginRetrofitApi()
                            ),
                            LoginLocalDataSource(InterrapidisimoApplication.database.userDao())
                        )
                    ),
                    getVersionUseCase = getVersionsUseCase,
                    compareVersionsUseCase = CompareVersionsUseCase(
                        getVersionUseCase = getVersionsUseCase
                    )
                )
            }
        }

    }

}