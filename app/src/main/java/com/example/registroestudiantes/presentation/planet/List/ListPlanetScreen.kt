package com.example.registroestudiantes.presentation.planet.List

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun ListPlanetScreen(
    onPlanetClick: (Int) -> Unit,
    viewModel: ListPlanetViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.value

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.error ?: "Error desconocido")
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.planets) { planet ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onClick = {
                            onPlanetClick(planet.id)
                        }
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            Text(
                                text = planet.name,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Image(
                                painter = rememberAsyncImagePainter(planet.image),
                                contentDescription = planet.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}