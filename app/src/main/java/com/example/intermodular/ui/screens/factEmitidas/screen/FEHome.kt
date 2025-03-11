package com.example.intermodular.ui.screens.factEmitidas.screen

import FEHomeViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.barraNavegacion.BottomNavigationBarComponent

@Composable
fun FEHome(viewModel: FEHomeViewModel, navController: NavHostController) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.cargarDatosUsuario(context)
    }

    val emailUsuario by viewModel.email.observeAsState() 

    val companias by viewModel.companias.observeAsState(initial = emptyList())
    val direcciones by viewModel.direcciones.observeAsState(initial = emptyList())
    val totales by viewModel.totales.observeAsState(initial = emptyList())

    LaunchedEffect(emailUsuario) {
        emailUsuario?.let {
            if (it.isNotEmpty()) {
                viewModel.obtenerFacturas(it)
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Invoices Sent",
                        color = Color(0xFF6F95AC),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(companias.indices.toList()) { i ->
                        CardForm(
                            companiaNombre = companias[i],
                            direccion = direcciones[i],
                            total = totales[i]
                        )
                    }
                }
            }

            // FAB flotante en la parte inferior derecha
            FloatingActionButton(
                onClick = { navController.navigate("FormularioFE") },
                containerColor = Color(0xFF6F95AC),
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Lo alinea en la parte inferior derecha
                    .padding(16.dp) // Espaciado desde los bordes
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Facturas")
            }
        }
    }
}


@Composable
fun CardForm(
    companiaNombre: String,
    direccion: String,
    total: Double
) {
    Spacer(modifier = Modifier.height(6.dp))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Fondo blanco para todo el Box
            .padding(8.dp) // Espaciado entre tarjetas
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center) // Centrar la tarjeta dentro del Box
                .width(400.dp) // Ancho específico para las tarjetas
                .shadow(8.dp, shape = shapes.medium) // Sombra
                .border(1.dp, color = Color.White, shape = shapes.medium), // Borde gris
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Establecer el color de fondo de la tarjeta
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Company: $companiaNombre")
                        Text(text = "Adress: $direccion")
                    }
                    Text(
                        text = "+ $${"%.2f".format(total)}",
                        color = Color(0xFF6F95AC),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
