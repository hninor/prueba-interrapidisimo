package com.hninor.pruebainterrapidisimo.features.menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hninor.pruebainterrapidisimo.R


@Composable
fun MenuScreen(onGoTablesScreen: () -> Unit, onGoPlacesScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { onGoTablesScreen() }, modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.tablas))
        }

        Button(onClick = { onGoPlacesScreen() }, modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.localidades))
        }
    }
}