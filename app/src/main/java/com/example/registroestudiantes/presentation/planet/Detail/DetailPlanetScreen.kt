package com.example.registroestudiantes.presentation.planet.Detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailPlanetScreen(
    planetId: Int,
    viewModel: DetailPlanetViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.value

    LaunchedEffect(planetId) {
        viewModel.loadPlanet(planetId)
    }

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
                Text(text = state.error!!)
            }
        }

        state.planet != null -> {

            val planet = state.planet!!

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = planet.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = rememberAsyncImagePainter(planet.image),
                    contentDescription = planet.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = planet.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}