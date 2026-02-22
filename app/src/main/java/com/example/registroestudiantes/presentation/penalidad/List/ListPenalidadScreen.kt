package com.example.registroestudiantes.presentation.penalidad.List

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.registroestudiantes.domain.model.TipoPenalidad
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPenalidadScreen(
    onEditar: (Int) -> Unit,
    onAgregar: () -> Unit,
    viewModel: ListPenalidadViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var penalidadAEliminar by remember { mutableStateOf<TipoPenalidad?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregar) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Penalidad")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                state.error != null -> Text(
                    text = state.error!!,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Lista de Penalidades",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(16.dp))

                        LazyColumn {
                            items(state.penalidades) { penalidad ->
                                PenalidadItem(
                                    penalidad = penalidad,
                                    onEditar = { onEditar(penalidad.tipoId) },
                                    onEliminar = {
                                        penalidadAEliminar = penalidad
                                    }
                                )
                            }
                        }
                    }
                }
            }

            penalidadAEliminar?.let { penalidad ->
                AlertDialog(
                    onDismissRequest = { penalidadAEliminar = null },
                    title = { Text("Eliminar Penalidad") },
                    text = { Text("¿Deseas eliminar la penalidad '${penalidad.nombre}'?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewModel.onEvent(ListPenalidadUIEvent.OnEliminar(penalidad))
                                penalidadAEliminar = null
                                scope.launch {
                                    snackbarHostState.showSnackbar("Penalidad eliminada")
                                }
                            }
                        ) { Text("Eliminar") }
                    },
                    dismissButton = {
                        TextButton(onClick = { penalidadAEliminar = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PenalidadItem(
    penalidad: TipoPenalidad,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = penalidad.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Descripción: ${penalidad.descripcion}")
                Text("Puntos: ${penalidad.puntosDescuento}")
            }

            Row {
                IconButton(onClick = onEditar) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar ${penalidad.nombre}")
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar ${penalidad.nombre}")
                }
            }
        }
    }
}