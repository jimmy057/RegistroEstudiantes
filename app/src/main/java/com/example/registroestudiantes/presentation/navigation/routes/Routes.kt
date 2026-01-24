package com.example.registroestudiantes.presentation.navigation.routes

sealed class Routes(val route: String) {

    object List : Routes("list_estudiantes")

    object Edit : Routes("edit_estudiante/{id}") {
        fun createRoute(id: Int = 0) = "edit_estudiante/$id"
    }
}
