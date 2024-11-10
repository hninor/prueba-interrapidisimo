package com.hninor.pruebainterrapidisimo.features.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hninor.pruebainterrapidisimo.core.ApiException
import com.hninor.pruebainterrapidisimo.core.DataException
import com.hninor.pruebainterrapidisimo.core.InterrapidisimoApplication
import com.hninor.pruebainterrapidisimo.features.login.data.local.LoginLocalDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.LoginRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.login.data.network.api.provideLoginRetrofitApi
import com.hninor.pruebainterrapidisimo.features.login.data.repository.LoginUserDataRepository
import com.hninor.pruebainterrapidisimo.features.login.domain.model.LoginResponse
import com.hninor.pruebainterrapidisimo.features.login.domain.use_cases.LoginUserUseCase
import kotlinx.coroutines.launch

sealed interface LoginUiState {
    data class Success(val loginResponse: LoginResponse) : LoginUiState
    data class Error(val message: String) : LoginUiState
    object Loading : LoginUiState
    object Home : LoginUiState
}

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) :
    ViewModel() {

    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Home)
        private set

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    LoginUserUseCase(
                        LoginUserDataRepository(
                            LoginRemoteDataSource(
                                provideLoginRetrofitApi()
                            ),
                            LoginLocalDataSource(InterrapidisimoApplication.database.userDao())
                        )
                    )
                )
            }
        }

    }

}