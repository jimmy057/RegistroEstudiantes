package com.example.registroestudiantes.presentation.planet.Detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetByIdUseCase

@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
    private val getPlanetByIdUseCase: GetPlanetByIdUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(DetailPlanetUIState())
    val uiState: State<DetailPlanetUIState> = _uiState

    fun loadPlanet(id: Int) {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val planet = getPlanetByIdUseCase(id)

                _uiState.value = _uiState.value.copy(
                    planet = planet,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }
}