package com.example.registroestudiantes.presentation.penalidad.Edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPenalidadScreen(
    penalidadId: Int = 0,
    viewModel: EditPenalidadViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(penalidadId) {
        if (penalidadId > 0) viewModel.setPenalidadId(penalidadId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = if (penalidadId == 0) "Registrar Penalidad" else "Editar Penalidad",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.nombre,
            onValueChange = { viewModel.onEvent(EditPenalidadUIEvent.OnNombreChange(it)) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.descripcion,
            onValueChange = { viewModel.onEvent(EditPenalidadUIEvent.OnDescripcionChange(it)) },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.puntos,
            onValueChange = { viewModel.onEvent(EditPenalidadUIEvent.OnPuntosChange(it)) },
            label = { Text("Puntos") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { viewModel.onEvent(EditPenalidadUIEvent.OnGuardar) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        state.mensaje?.let {
            Spacer(Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.primary)
        }

        state.error?.let {
            Spacer(Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
