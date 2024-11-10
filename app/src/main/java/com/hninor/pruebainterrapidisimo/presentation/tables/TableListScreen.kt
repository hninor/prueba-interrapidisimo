package com.hninor.pruebainterrapidisimo.presentation.tables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hninor.pruebainterrapidisimo.R
import com.hninor.pruebainterrapidisimo.domain.model.Table
import com.hninor.pruebainterrapidisimo.presentation.theme.TableListUiState

@Composable
fun TableListScreen(
    tableListUiState: TableListUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (tableListUiState) {
        is TableListUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is TableListUiState.Success -> TablesGridScreen(
            tables = tableListUiState.tables,
            modifier = modifier
        )

        is TableListUiState.Error -> ErrorScreen(retryAction)
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

    Column {
        Text(text = stringResource(id = R.string.error))
        Button(onClick = { retryAction() }) {
            Text(text = stringResource(id = R.string.intentar_nuevamente))
        }
    }
}


@Composable
fun TablesGridScreen(tables: List<Table>, modifier: Modifier) {
    // Display a grid of photos using LazyVerticalGrid
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
    ) {
        items(tables) {
            TableItem(item = it)
        }
    }
}

@Composable
fun TableItem(item: Table) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.nombre, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = item.queryCreacion)
            Row {
                Text(text = stringResource(id = R.string.numero_campos))

                Text(text = item.numeroCampos.toString())
            }

        }
    }
}