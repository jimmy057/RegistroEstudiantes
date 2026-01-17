package com.example.registroestudiantes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.registroestudiantes.presentation.navigation.AppNavHost.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import com.example.registroestudiantes.ui.theme.RegistroEstudiantesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegistroEstudiantesTheme {
                AppNavHost(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

