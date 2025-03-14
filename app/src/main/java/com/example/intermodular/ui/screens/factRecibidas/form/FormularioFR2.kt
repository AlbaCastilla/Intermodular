package com.example.intermodular.ui.screens.factRecibidas.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel
import com.example.intermodular.navigation.AppScreens
import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFEViewModel


@Composable
fun FormularioConProgreso2(viewModel: FormularioFRViewModel, indicadorProgresoViewModel: IndicadorProgresoViewModel, navController: NavHostController) {
    val valor by viewModel.valor.observeAsState("")
    val iva by viewModel.iva.observeAsState(initial = 21) // Default to 21%
    val total by viewModel.total.observeAsState(0.0)

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Text(text = "Formulario con IVA y Total")

            IndicadorProgreso(
                viewModel = indicadorProgresoViewModel,
                pasosTotales = 3
            )

            CardForm(
                valor = valor,
                iva = iva,
                total = total,
                viewModel = viewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForm(valor: String, iva: Int, total: Double, viewModel: FormularioFRViewModel, indicadorProgresoViewModel: IndicadorProgresoViewModel, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val ivaOptions = listOf(21, 4, 10)
    var selectedIva by remember { mutableStateOf(iva) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Invoice Information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Fill in the invoice details below",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = valor,
                onValueChange = { viewModel.actualizarValor(it) },
                singleLine = true,
                label = { Text("Sum") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // IVA Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = "$selectedIva%",
                    onValueChange = { },
                    label = { Text("VAT") },
                    trailingIcon = {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                    },
                    readOnly = true,
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    ivaOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text("$option%") },
                            onClick = {
                                selectedIva = option
                                expanded = false
                                viewModel.actualizarIVA(option) // Update the IVA value in the ViewModel
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            /*
                        Text("IVA: $selectedIva%")
                        Spacer(modifier = Modifier.height(12.dp))*/

            Text("Total: $total")
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    indicadorProgresoViewModel.avanzarPaso(3)
                    navController.navigate(AppScreens.FormularioFR3.ruta)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF577A8E) // Button color
                ),
                shape = RoundedCornerShape(8.dp)

            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "Siguiente",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(2.dp)) // Adding space before the button
            Button(
                onClick = { navController.navigate("FormularioFR") /*viewModel.guardarFacturaEnFirestore() */} ,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally), // Centering the button horizontally
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF577A8E) // Button color
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Back",
                    fontSize = 16.sp,
                    color = Color.White, // Button text color
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Atras",
                    tint = Color.White
                )
            }
        }
    }
}

