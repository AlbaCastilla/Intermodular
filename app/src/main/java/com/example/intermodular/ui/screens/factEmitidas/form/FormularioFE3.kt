package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel
import com.example.intermodular.navigation.AppScreens


@Composable
fun FuncionesFormularioFE3(
    viewModel: FormularioFEViewModel,
    indicadorProgresoViewModel: IndicadorProgresoViewModel,
    navController: NavHostController
) {
    // Observe LiveData values
    val companiaNombre by viewModel.companiaNombre.observeAsState("")
    val nif by viewModel.nif.observeAsState("")
    val direccion by viewModel.direccion.observeAsState("")
    val valor by viewModel.valor.observeAsState(0.0)
    val iva by viewModel.iva.observeAsState(21)
    val total by viewModel.total.observeAsState(0.0)


    val companiaNombreUsuario by viewModel.companiaNombreUsuario.observeAsState("")
    val nifUsuario by viewModel.nifUsuario.observeAsState("")
    val direccionUsuario by viewModel.direccionUsuario.observeAsState("")
    val emailUsuario by viewModel.emailUsuario.observeAsState("")
    val phoneNumberUsuario by viewModel.phoneNumberUsuario.observeAsState("")
    val displayNameUsuario by viewModel.displayNameUsuario.observeAsState("")


    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.cargarDatosUsuario(context)
    }
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

        CardForm2(
            companiaNombre = companiaNombre,
            nif = nif,
            direccion = direccion,
            valor = valor,
            iva =iva,
            total =total,
            companiaNombreUsuario = companiaNombreUsuario,
            nifUsuario = nifUsuario,
            direccionUsuario = direccionUsuario,
            emailUsuario = emailUsuario,
            phoneNumberUsuario = phoneNumberUsuario,
            displayNameUsuario = displayNameUsuario,
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
fun CardForm2(
    companiaNombre: String,
    nif: String,
    direccion: String,
    valor: Comparable<*>,
    iva: Int,
    total: Double,
    companiaNombreUsuario: String,
    nifUsuario: String,
    direccionUsuario: String,
    emailUsuario: String,
    phoneNumberUsuario: String,
    displayNameUsuario: String,
    viewModel: FormularioFEViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Render the form using passed parameters
        Card(
            modifier = Modifier
                .padding(16.dp) // Adding margin around the card
                .fillMaxWidth(0.9f), // Adjust the width of the card to have some margin
            elevation = CardDefaults.cardElevation(8.dp), // Giving some shadow to the card
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EFEF)) // Setting the card background color // Setting the card background color
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Hecho por Section
                Text(
                    text = "Made by:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = "User: $companiaNombreUsuario")
                Text(text = "User TIN: $nifUsuario")
                Text(text = "User Address: $direccionUsuario")
                Text(text = "User Email: $emailUsuario")

                // Divider
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color(0xFF9D9595), // Divider color
                    thickness = 2.dp
                )

                // Datos de la factura Section
                Text(
                    text = "Invoice Data:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = "Company: $companiaNombre")
                Text(text = "TIN: $nif")
                Text(text = "Adress: $direccion")
                Text(text = "Sum: $valor")
                Text(text = "VAT: $iva")
                Text(text = "Total Sum: $total")

                // Save button
                Spacer(modifier = Modifier.height(16.dp)) // Adding space before the button
                Button(
                    onClick = {
                        viewModel.guardarFacturaEnFirestore()
                        navController.navigate(AppScreens.FEHome.ruta)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Centering the button horizontally
                        .fillMaxWidth(0.6f) // Adjusting the button width to 60% of screen
                        .padding(vertical = 8.dp), // Adding padding around the button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF577A8E) // Button color
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Save",
                        color = Color.White, // Button text color
                        fontSize = 16.sp,
                    )
                }
                Button(
                    onClick = { navController.navigate("FormularioFE2") /*viewModel.guardarFacturaEnFirestore() */} ,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Centering the button horizontally
                        .fillMaxWidth(0.6f) // Adjusting the button width to 60% of screen
                        .padding(vertical = 8.dp), // Adding padding around the button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF577A8E) // Button color
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Back",
                        color = Color.White, // Button text color
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Siguiente",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

