package com.example.intermodular.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import com.example.intermodular.componentes.barraNavegacion.NavItem

sealed class AppScreens(val ruta: String){
    object Home: AppScreens("Home")
    object Login: AppScreens("Login")
    object Registro: AppScreens("Registro")
    object FormularioFE: AppScreens("FormularioFE")
    object FormularioFE2: AppScreens("FormularioFE2")
    object FormularioFE3: AppScreens("FormularioFE3")

    val navItems = listOf(
        NavItem("FormularioFE", Icons.Default.Send, "Sent"),
        NavItem("home", Icons.Default.Home, "Home"),
        NavItem("received", Icons.Default.Star, "Received")
    )

    object IndicadorProgreso: AppScreens("IndicadorProgreso")
}
