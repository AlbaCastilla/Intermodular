package com.example.intermodular.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import com.example.intermodular.componentes.barraNavegacion.NavItem

class NavigationViewModel : ViewModel() {
    val navItems = listOf(
        NavItem("FEHome", Icons.Default.Send, "Sent"),
        NavItem("home", Icons.Default.Home, "Home"),
        NavItem("FRHome", Icons.Default.Star, "Received")
    )
}
