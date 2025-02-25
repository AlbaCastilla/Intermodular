package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel
import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFEViewModel2
@Composable
fun FormularioFE2(
    viewModel: FormularioFEViewModel2,
    indicadorProgresoViewModel: IndicadorProgresoViewModel,
    navController: NavHostController
) {
    // Observe the clienteId LiveData
    val clienteId by viewModel.clienteId.observeAsState()

    // Display the clienteId or a default message if null
    Text(text = "Cliente ID: ${clienteId ?: "No ID available"}")
}