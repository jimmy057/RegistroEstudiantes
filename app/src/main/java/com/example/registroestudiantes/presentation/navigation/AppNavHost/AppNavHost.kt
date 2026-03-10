package com.example.registroestudiantes.presentation.navigation.AppNavHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.registroestudiantes.presentation.navigation.routes.Routes
import com.example.registroestudiantes.presentation.tareas.edit.EditEstudianteScreen
import com.example.registroestudiantes.presentation.tareas.list.ListEstudianteScreen
import com.example.registroestudiantes.presentation.asignatura.Edit.EditAsignaturaScreen
import com.example.registroestudiantes.presentation.asignatura.List.ListAsignaturaScreen
import com.example.registroestudiantes.presentation.penalidad.Edit.EditPenalidadScreen
import com.example.registroestudiantes.presentation.penalidad.List.ListPenalidadScreen
import com.example.registroestudiantes.presentation.planet.List.ListPlanetScreen
import com.example.registroestudiantes.presentation.planet.Detail.DetailPlanetScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.List.route,
        modifier = modifier
    ) {


        composable(Routes.List.route) {
            ListEstudianteScreen(
                onEditar = { id ->
                    navController.navigate(Routes.Edit.createRoute(id))
                },
                onAgregar = {
                    navController.navigate(Routes.Edit.createRoute(0))
                },
                onIrAsignaturas = {
                    navController.navigate(Routes.ListAsignaturas.route)
                }
            )
        }

        composable(
            route = Routes.Edit.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            EditEstudianteScreen()
        }


        composable(Routes.ListAsignaturas.route) {
            ListAsignaturaScreen(
                onEditar = { id ->
                    navController.navigate(Routes.EditAsignatura.createRoute(id))
                },
                onAgregar = {
                    navController.navigate(Routes.EditAsignatura.createRoute(0))
                }
            )
        }

        composable(
            route = Routes.EditAsignatura.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            EditAsignaturaScreen()
        }

        composable(Routes.ListPenalidades.route) {
            ListPenalidadScreen(
                onEditar = { id ->
                    navController.navigate(Routes.EditPenalidad.createRoute(id))
                },
                onAgregar = {
                    navController.navigate(Routes.EditPenalidad.createRoute(0))
                }
            )
        }

        composable(
            route = Routes.EditPenalidad.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            EditPenalidadScreen()
        }


        composable(Routes.ListPlanets.route) {
            ListPlanetScreen(
                onPlanetClick = { id ->
                    navController.navigate(
                        Routes.DetailPlanet.createRoute(id)
                    )
                }
            )
        }

        composable(
            route = Routes.DetailPlanet.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailPlanetScreen()
        }
    }
}

