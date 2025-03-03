package com.example.intermodular.componentes.barraNavegacion

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.intermodular.viewmodels.NavigationViewModel

@Composable
fun BottomNavigationBarComponent(
    navController: NavController,
    viewModel: NavigationViewModel = viewModel()
) {
    val selectedRoute by navController.currentBackStackEntryAsState()

    NavigationBar(containerColor = Color(0xFFE6E6FA)) {
        viewModel.navItems.forEach { item ->
            val isSelected = item.route == selectedRoute?.destination?.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(if (isSelected) 30.dp else 24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = if (isSelected) 12.sp else 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}


