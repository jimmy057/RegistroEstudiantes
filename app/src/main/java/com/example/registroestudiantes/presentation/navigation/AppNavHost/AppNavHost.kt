package com.example.registroestudiantes.presentation.navigation.AppNavHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                onEditar = { id ->
                    navController.navigate(
                        Routes.Edit.createRoute(id)
                    )
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
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            EditEstudianteScreen(estudianteId = id)
        }
    }
}
