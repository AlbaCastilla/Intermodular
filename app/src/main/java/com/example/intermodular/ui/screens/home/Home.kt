package com.example.intermodular.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intermodular.componentes.barraNavegacion.BottomNavigationBarComponent

@Composable
fun Home(viewModel: HomeViewModel) {
Text(text = "Hola desde Home")

    val navHostController = rememberNavController()
    BottomNavigationBarComponent(navController = navHostController)
}