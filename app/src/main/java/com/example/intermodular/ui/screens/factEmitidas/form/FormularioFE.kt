package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel
import com.example.intermodular.navigation.AppScreens


/**
 * FORMULARIO DE AÑADIR FACTURA
 */


@Composable
fun FormularioFE(viewModel: FormularioFEViewModel, indicadorProgresoViewModel: IndicadorProgresoViewModel, navController: NavHostController) {

    //declaracion de las variables de nuestro viewModel
    val companiaNombre: String by viewModel.companiaNombre.observeAsState(initial = "")
    val nif: String by viewModel.nif.observeAsState(initial = "")
    val direccion: String by viewModel.direccion.observeAsState(initial = "")

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Text(text = "Login")

            //indicador de progreso
//            IndicadorProgreso(
//                viewModel = indicadorProgresoViewModel,
//                pasosTotales = 3
//            )

            //las llamadas al resto de funciones las haremos en FuncionesLogin
            FuncionesFormularioFE(
                viewModel = viewModel,
                companiaNombre = companiaNombre,
                nif = nif,
                direccion = direccion,
                indicadorProgresoViewModel = indicadorProgresoViewModel,
                navController = navController
            )
        }
    }

}

@Composable
fun CardForm(companiaNombre: String, nif: String, direccion: String, indicadorProgresoViewModel: IndicadorProgresoViewModel, viewModel: FormularioFEViewModel, navController: NavHostController) {
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
                text = "Invoice's receiver information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please complete the following fields on the invoice receiver's information",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Company/personal full name",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = companiaNombre,
                onValueChange = { viewModel.actualizarCompaniaNombre(it) },
                singleLine = true,
                label = { Text("Jane Doe") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "TIN",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = nif,
                onValueChange = { viewModel.actualizarNif(it) },
                singleLine = true,
                label = { Text("00000000P") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Adress",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = direccion,
                onValueChange = { viewModel.actualizarDireccion(it) },
                singleLine = true,
                label = { Text("xxxxxx") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                     /*viewModel.guardarFacturaEmitida(companiaNombre, nif, direccion)*/
                     indicadorProgresoViewModel.avanzarPaso(3)
                    navController.navigate(AppScreens.FormularioFE2.ruta)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF577A8E) // Button color
                ),
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
        }
    }
}


@Composable
fun FuncionesFormularioFE(
    viewModel: FormularioFEViewModel,
    companiaNombre: String,
    nif: String,
    direccion: String,
    indicadorProgresoViewModel: IndicadorProgresoViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IndicadorProgreso(
            viewModel = indicadorProgresoViewModel,
            pasosTotales = 3
        )

        CardForm(
            companiaNombre = companiaNombre,
            nif = nif,
            direccion = direccion,
            viewModel = viewModel, // Pasamos el ViewModel
            indicadorProgresoViewModel = indicadorProgresoViewModel,
            navController = navController
        )
    }
}
