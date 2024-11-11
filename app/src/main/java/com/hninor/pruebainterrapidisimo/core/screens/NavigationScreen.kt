package com.hninor.pruebainterrapidisimo.core.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hninor.pruebainterrapidisimo.features.localidades.presentation.LocalidadesApp
import com.hninor.pruebainterrapidisimo.features.login.presentation.LoginApp
import com.hninor.pruebainterrapidisimo.features.menu.presentation.MenuScreen
import com.hninor.pruebainterrapidisimo.features.splash.presentation.SplashScreen
import com.hninor.pruebainterrapidisimo.features.tablas.presentation.TablasApp


enum class InterrapidisimoScreen {
    Splash,
    Login,
    Menu,
    Tables,
    Places
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val navigateToMenu = {
        navController.navigate(InterrapidisimoScreen.Menu.name) {
            popUpTo(InterrapidisimoScreen.Login.name) {
                inclusive = true
            }
        }
    }

    NavHost(navController = navController, startDestination = InterrapidisimoScreen.Splash.name) {

        composable(route = InterrapidisimoScreen.Splash.name) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(route = InterrapidisimoScreen.Login.name)
            }
        }

        composable(route = InterrapidisimoScreen.Login.name) {
            LoginApp(navigateToMenu)
        }

        composable(route = InterrapidisimoScreen.Menu.name) {
            MenuScreen(onGoTablesScreen = { navController.navigate(InterrapidisimoScreen.Tables.name) }) {
                navController.navigate(InterrapidisimoScreen.Places.name)
            }
        }

        composable(route = InterrapidisimoScreen.Tables.name) {
            TablasApp {
                if (!navController.popBackStack()) {
                    navController.navigate(InterrapidisimoScreen.Menu.name)
                }

            }
        }

        composable(route = InterrapidisimoScreen.Places.name) {
            LocalidadesApp {
                if (!navController.popBackStack()) {
                    navController.navigate(InterrapidisimoScreen.Menu.name)
                }
            }
        }
    }

}