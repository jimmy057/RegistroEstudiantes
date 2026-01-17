package com.example.registroestudiantes.presentation.tareas.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registroestudiantes.domain.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEstudianteScreen(
    onAddClick: () -> Unit,
    viewModel: ListEstudianteViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.cargar()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de estudiantes") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+")
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.estudiantes.isEmpty()) {
                Text("No hay estudiantes registrados")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.estudiantes) { estudiante ->
                        EstudianteItem(
                            estudiante = estudiante,
                            onEliminar = {
                                viewModel.eliminar(it)
                            }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun EstudianteItem(
    estudiante: Estudiante,
    onEliminar: (Estudiante) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = estudiante.nombres,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = estudiante.email)
                Text(text = "Edad: ${estudiante.edad}")
            }

            IconButton(onClick = { onEliminar(estudiante) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}

