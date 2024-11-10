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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hninor.pruebainterrapidisimo.core.theme.PruebaInterrapidisimoTheme
import com.hninor.pruebainterrapidisimo.features.localidades.presentation.LocalidadListScreen
import com.hninor.pruebainterrapidisimo.features.localidades.presentation.LocalidadListViewModel
import com.hninor.pruebainterrapidisimo.features.login.presentation.LoginScreenHome
import com.hninor.pruebainterrapidisimo.features.login.presentation.LoginViewModel
import com.hninor.pruebainterrapidisimo.features.menu.presentation.MenuScreen
import com.hninor.pruebainterrapidisimo.features.tablas.presentation.TableListScreen
import com.hninor.pruebainterrapidisimo.presentation.theme.TableListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaInterrapidisimoTheme {
                AppHost()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TablasApp() {
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

@Composable
fun AppHost() {
    val navController = rememberNavController()
    val onLoginSuccess = {
        navController.navigate(InterrapidisimoScreen.Menu.name)
    }

    NavHost(navController, startDestination = InterrapidisimoScreen.Login.name) {

        composable(route = InterrapidisimoScreen.Login.name) {
            LoginApp(onLoginSuccess)
        }

        composable(route = InterrapidisimoScreen.Menu.name) {
            MenuScreen(onGoTablesScreen = { navController.navigate(InterrapidisimoScreen.Tables.name) }) {
                navController.navigate(InterrapidisimoScreen.Places.name)
            }
        }

        composable(route = InterrapidisimoScreen.Tables.name) {
            TablasApp()
        }

        composable(route = InterrapidisimoScreen.Places.name) {
            LocalidadesApp()
        }
    }

}

@Composable
fun LoginApp(onLoginSuccess: () -> Unit) {
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.inversePrimary
    ) {

        LoginScreenHome(
            loginUiState = viewModel.loginUiState,
            onLoginButtonClick = viewModel::login,
            onConfirmation = viewModel::onConfirmation,
            onLoginSuccess = onLoginSuccess
        )
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


enum class InterrapidisimoScreen() {
    Login,
    Menu,
    Tables,
    Places
}