package com.example.registroestudiantes.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.registroestudiantes.presentation.navigation.AppNavHost.AppNavHost
import com.example.registroestudiantes.presentation.navigation.routes.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItems = listOf(
        DrawerItem("Estudiantes", Routes.List.route, Icons.Default.Person),
        DrawerItem("Asignaturas", Routes.ListAsignaturas.route, Icons.Default.List),
        DrawerItem("Penalidades", Routes.ListPenalidades.route, Icons.Default.Warning),
        DrawerItem("Planetas", Routes.ListPlanets.route, Icons.Default.Public)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {

                Text(
                    text = "Menú",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider()

                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route

                menuItems.forEach { item ->

                    NavigationDrawerItem(
                        label = { Text(item.title) },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        selected = currentRoute == item.route,
                        onClick = {

                            navController.navigate(item.route) {

                                launchSingleTop = true

                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }

                                restoreState = true
                            }

                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier.padding(
                            NavigationDrawerItemDefaults.ItemPadding
                        )
                    )
                }
            }
        }
    ) {

        Scaffold(

            topBar = {

                CenterAlignedTopAppBar(

                    title = {
                        Text("Registro de Estudiantes")
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "Abrir menú"
                            )
                        }
                    }
                )
            }

        ) { innerPadding ->

            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

data class DrawerItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)
