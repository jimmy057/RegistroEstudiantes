package com.example.registroestudiantes.presentation.navigation.routes

sealed class Routes(val route: String) {

    object List : Routes("list_estudiantes")

    object Edit : Routes("edit_estudiante/{id}") {
        fun createRoute(id: Int = 0) = "edit_estudiante/$id"
    }

    object ListAsignaturas : Routes("list_asignaturas")

    object EditAsignatura : Routes("edit_asignatura/{id}") {
        fun createRoute(id: Int = 0) = "edit_asignatura/$id"
    }

    object ListPenalidades : Routes("list_penalidades")

    object EditPenalidad : Routes("edit_penalidad/{id}") {
        fun createRoute(id: Int = 0) = "edit_penalidad/$id"
    }
}
