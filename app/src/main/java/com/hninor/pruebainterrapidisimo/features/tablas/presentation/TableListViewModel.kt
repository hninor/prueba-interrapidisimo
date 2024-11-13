package com.hninor.pruebainterrapidisimo.presentation.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hninor.pruebainterrapidisimo.core.InterrapidisimoApplication
import com.hninor.pruebainterrapidisimo.core.InterrapidisimoApplication.Companion.appContext
import com.hninor.pruebainterrapidisimo.features.tablas.data.local.TablesLocalDataSource
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.TablesRemoteDataSource
import com.hninor.pruebainterrapidisimo.features.tablas.data.network.api.provideTableRetrofitApi
import com.hninor.pruebainterrapidisimo.features.tablas.data.repository.TablesDataRepository
import com.hninor.pruebainterrapidisimo.features.tablas.domain.model.Table
import com.hninor.pruebainterrapidisimo.features.tablas.domain.use_cases.GetTablesUseCase
import kotlinx.coroutines.launch

sealed interface TableListUiState {
    data class Success(val tables: List<Table>) : TableListUiState
    object Error : TableListUiState
    object Loading : TableListUiState
}

class TableListViewModel(private val getTablesUseCase: GetTablesUseCase) : ViewModel() {

    var tableListUiState: TableListUiState by mutableStateOf(TableListUiState.Loading)
        private set

    init {

        getTables()


    }

    fun getTables() {
        viewModelScope.launch {
            tableListUiState = TableListUiState.Loading

            tableListUiState = try {
                TableListUiState.Success(getTablesUseCase.invoke("pam.meredy21", "987204545"))
            } catch (e: Exception) {
                TableListUiState.Error
            }


        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TableListViewModel(
                    GetTablesUseCase(
                        TablesDataRepository(
                            TablesRemoteDataSource(
                                provideTableRetrofitApi()
                            ), TablesLocalDataSource(InterrapidisimoApplication.database.tableDao(), appContext)
                        )
                    )
                )
            }
        }
    }

}