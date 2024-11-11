package com.hninor.pruebainterrapidisimo.features.login.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hninor.pruebainterrapidisimo.R
import com.hninor.pruebainterrapidisimo.core.theme.primaryFontFamily


@Composable
fun LoginApp(navigateToMenu: () -> Unit) {
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.inversePrimary
    ) {

        LoginScreenHome(
            loginUiState = viewModel.loginUiState,
            onLoginButtonClick = viewModel::login,
            onConfirmation = viewModel::onConfirmation,
            navigateToMenu = navigateToMenu,
            version = viewModel.version
        )
    }

}


@Composable
fun LoginScreenHome(
    loginUiState: LoginUiState,
    onLoginButtonClick: (username: String, password: String) -> Unit,
    onConfirmation: () -> Unit,
    navigateToMenu: () -> Unit,
    version: String
) {
    val context = LocalContext.current
    when (loginUiState) {
        is LoginUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is LoginUiState.Success -> {
            Toast.makeText(
                context,
                "Bienvenido(a) ${loginUiState.loginResponse.nombre}!",
                Toast.LENGTH_LONG
            ).show()
            navigateToMenu()
        }

        is LoginUiState.Error -> ErrorScreen(loginUiState.message, onConfirmation)
        is LoginUiState.Home -> LoginScreen(
            modifier = Modifier.fillMaxSize(),
            onLoginButtonClick = onLoginButtonClick,
            version = version
        )

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
fun LoginScreen(
    modifier: Modifier,
    onLoginButtonClick: (username: String, password: String) -> Unit,
    version: String
) {
    // Display a grid of photos using LazyVerticalGrid

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.dog_content_description)
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                modifier = Modifier.padding(16.dp),
                singleLine = true
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.padding(16.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )
            Button(
                onClick = { onLoginButtonClick(username, password) },
                modifier = Modifier.padding(16.dp),
                enabled = username.isNotEmpty() && password.isNotEmpty()
            ) {
                Text(text = stringResource(id = R.string.login))
            }

        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            text = "Versión: $version",
            fontFamily = primaryFontFamily,
            color = MaterialTheme.colorScheme.primary
        )
    }


}

@Preview
@Composable
fun PreviewLogin() {
    LoginScreen(modifier = Modifier, onLoginButtonClick = { a, b -> }, version = "45")
}

