package com.example.intermodular.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.intermodular.componentes.barraNavegacion.BottomNavigationBarComponent

@Composable
fun Home(viewModel: HomeViewModel) {
    Text(text = "Hola desde Home")

    // Inicializa el NavController dentro del Composable
    val navHostController = rememberNavController()

    // Pasa el NavController al componente de la barra de navegación
    BottomNavigationBarComponent(navController = navHostController)
}
