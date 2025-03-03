package com.example.intermodular.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intermodular.componentes.barraNavegacion.BottomNavigationBarComponent

@Composable
fun Home(viewModel: HomeViewModel, navController: NavHostController) {
    Text(text = "Hola desde Home")

    // Usa el NavController que se pasa desde el NavigationWrapper
    BottomNavigationBarComponent(navController = navController)
}

