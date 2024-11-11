package com.hninor.pruebainterrapidisimo.features.localidades.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hninor.pruebainterrapidisimo.R
import com.hninor.pruebainterrapidisimo.core.screens.InterrapidisimoTopAppBar
import com.hninor.pruebainterrapidisimo.features.localidades.domain.model.Localidad


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalidadesApp(onBack: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InterrapidisimoTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(id = R.string.localidades),
                onBack = onBack
            )
        }
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
fun LocalidadListScreen(
    localidadListUiState: LocalidadListUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (localidadListUiState) {
        is LocalidadListUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is LocalidadListUiState.Success -> LocalidadesGridScreen(
            localidades = localidadListUiState.localidades,
            modifier = modifier
        )

        is LocalidadListUiState.Error -> ErrorScreen(retryAction)
        else -> {

        }
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
fun ErrorScreen(retryAction: () -> Unit) {
    // Display an error message with a retry button

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.error_localidades), Modifier.padding(16.dp))
        Button(onClick = { retryAction() }) {
            Text(text = stringResource(id = R.string.intentar_nuevamente))
        }
    }
}


@Composable
fun LocalidadesGridScreen(localidades: List<Localidad>, modifier: Modifier) {
    // Display a grid of photos using LazyVerticalGrid
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
    ) {
        items(localidades) {
            LocalidadItem(item = it)
        }
    }
}

@Composable
fun LocalidadItem(item: Localidad) {
    Card (modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.abreviacion, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = item.nombre)

        }
    }
}