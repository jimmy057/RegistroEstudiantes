package com.example.registroestudiantes.presentation.tareas.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.registroestudiantes.domain.model.Estudiante

@Composable
fun ListEstudianteScreen(
    onEditar: (Int) -> Unit,
    viewModel: ListEstudianteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListEstudianteContent(
        state = state,
        onEditar = onEditar,
        onEliminar = viewModel::eliminar
    )
}


@Composable
fun ListEstudianteContent(
    state: ListEstudianteUIState,
    onEditar: (Int) -> Unit,
    onEliminar: (Estudiante) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEditar(0) }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Lista de Estudiantes",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.estudiantes.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No hay estudiantes registrados")
                    }
                }

                else -> {
                    LazyColumn {
                        items(state.estudiantes) { estudiante ->
                            EstudianteItem(
                                estudiante = estudiante,
                                onEditar = { onEditar(estudiante.estudianteId) },
                                onEliminar = { onEliminar(estudiante) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListEstudianteScreenPreview() {
    MaterialTheme {
        ListEstudianteContent(
            state = ListEstudianteUIState(
                isLoading = false,
                estudiantes = listOf(
                    Estudiante(1, "Juan", "juan@mail.com", 20),
                    Estudiante(2, "Ana", "ana@mail.com", 22)
                )
            ),
            onEditar = {},
            onEliminar = {}
        )
    }
}

@Composable
fun EstudianteItem(
    estudiante: Estudiante,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = estudiante.nombres,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = estudiante.email)
                Text(text = "Edad: ${estudiante.edad}")
            }

            Row {
                IconButton(onClick = onEditar) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }

                IconButton(onClick = onEliminar) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
            }
        }
    }
}





