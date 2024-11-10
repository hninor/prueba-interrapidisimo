package com.hninor.pruebainterrapidisimo.features.localidades.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.LocalidadesRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.localidades.data.network.api.provideLocalidadRetrofitApi
import com.hninor.pruebainterrapidisimo.features.localidades.data.repository.LocalidadesDataRepository
import com.hninor.pruebainterrapidisimo.features.localidades.domain.model.Localidad
import com.hninor.pruebainterrapidisimo.features.localidades.domain.use_cases.GetLocalidadesUseCase
import kotlinx.coroutines.launch

sealed interface LocalidadListUiState {
    data class Success(val localidades: List<Localidad>) : LocalidadListUiState
    object Error : LocalidadListUiState
    object Loading : LocalidadListUiState
}

class LocalidadListViewModel(private val getLocalidadesUseCase: GetLocalidadesUseCase) :
    ViewModel() {

    var localidadListUiState: LocalidadListUiState by mutableStateOf(LocalidadListUiState.Loading)
        private set

    init {

        getLocalidades()


    }

    fun getLocalidades() {
        viewModelScope.launch {
            localidadListUiState = LocalidadListUiState.Loading

            localidadListUiState = try {
                LocalidadListUiState.Success(getLocalidadesUseCase())
            } catch (e: Exception) {
                LocalidadListUiState.Error
            }


        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LocalidadListViewModel(
                    GetLocalidadesUseCase(
                        LocalidadesDataRepository(
                            LocalidadesRemoteDataSource(
                                provideLocalidadRetrofitApi()
                            )
                        )
                    )
                )
            }
        }

    }

}