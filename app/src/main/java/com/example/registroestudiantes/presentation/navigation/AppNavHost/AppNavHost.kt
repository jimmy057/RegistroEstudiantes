package com.example.registroestudiantes.presentation.navigation.AppNavHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registroestudiantes.presentation.navigation.routes.Routes
import com.example.registroestudiantes.presentation.tareas.edit.EditEstudianteScreen
import com.example.registroestudiantes.presentation.tareas.list.ListEstudianteScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.List.route,
        modifier = modifier
    ) {
        composable(Routes.List.route) {
            ListEstudianteScreen(
                onAddClick = {
                    navController.navigate(Routes.Edit.route)
                }
            )
        }

        composable(Routes.Edit.route) {
            EditEstudianteScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}