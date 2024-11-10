package com.hninor.pruebainterrapidisimo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hninor.pruebainterrapidisimo.core.theme.PruebaInterrapidisimoTheme
import com.hninor.pruebainterrapidisimo.features.localidades.presentation.LocalidadListScreen
import com.hninor.pruebainterrapidisimo.features.localidades.presentation.LocalidadListViewModel
import com.hninor.pruebainterrapidisimo.features.tablas.presentation.TableListScreen
import com.hninor.pruebainterrapidisimo.presentation.theme.TableListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaInterrapidisimoTheme {
                LocalidadesApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterrapidisimoApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { InterrapidisimoTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val tableListViewModel: TableListViewModel =
                viewModel(factory = TableListViewModel.Factory)
            TableListScreen(
                tableListUiState = tableListViewModel.tableListUiState,
                retryAction = tableListViewModel::getTables,
                modifier = Modifier.padding(it)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalidadesApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { InterrapidisimoTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val viewModel: LocalidadListViewModel =
                viewModel(factory = LocalidadListViewModel.Factory)
            LocalidadListScreen(
                localidadListUiState = viewModel.localidadListUiState,
                retryAction = viewModel::getLocalidades,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterrapidisimoTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}