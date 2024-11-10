package com.hninor.pruebainterrapidisimo.features.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.hninor.pruebainterrapidisimo.R


@Composable
fun LoginScreenHome(
    loginUiState: LoginUiState,
    onLogin: (username: String, password: String) -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (loginUiState) {
        is LoginUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is LoginUiState.Success -> SuccessScreen(loginUiState.loginResponse.nombre, onConfirmation)
        is LoginUiState.Error -> ErrorScreen(loginUiState.message, onConfirmation)
        is LoginUiState.Home -> LoginScreen(modifier = modifier, onLogin = onLogin)

    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    // Display a loading indicator
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "loading-wheel" },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Red
        )
    }
}


@Composable
fun ErrorScreen(message: String, onConfirmation: () -> Unit) {

    AlertDialogExample(
        onConfirmation = onConfirmation,
        dialogTitle = "Atención",
        dialogText = message,
        icon = Icons.Default.Info
    )

}

@Composable
fun SuccessScreen(nombre: String, onConfirmation: () -> Unit) {

    AlertDialogExample(
        onConfirmation = onConfirmation,
        dialogTitle = "Atención",
        dialogText = "Bienvenido(a) $nombre!",
        icon = Icons.Default.Info
    )

}


@Composable
fun AlertDialogExample(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {

        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Aceptar")
            }
        }
    )
}

@Composable
fun LoginScreen(modifier: Modifier, onLogin: (username: String, password: String) -> Unit) {
    // Display a grid of photos using LazyVerticalGrid

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") }, modifier = Modifier.padding(16.dp)
        )
        Button(onClick = { onLogin(username, password) }, modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.login))
        }

    }

}

