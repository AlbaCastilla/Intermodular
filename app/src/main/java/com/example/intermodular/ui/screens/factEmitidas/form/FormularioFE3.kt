package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel


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
    iva:Int,
    total:Double,
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
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Compañía: $companiaNombre")
                Text(text = "NIF: $nif")
                Text(text = "Dirección: $direccion")
                Text(text = "Valor: $valor")
                Text(text = "IVA: $iva")
                Text(text = "Total: $total")
                Text(text = "Compañía Usuario: $companiaNombreUsuario")
                Text(text = "NIF Usuario: $nifUsuario")
                Text(text = "Dirección Usuario: $direccionUsuario")
                Text(text = "Email Usuario: $emailUsuario")
                Text(text = "Teléfono Usuario: $phoneNumberUsuario")
                Text(text = "Nombre Usuario: $displayNameUsuario")

                Button(onClick = { viewModel.guardarFacturaEnFirestore() }) {
                    Text("Guardar")
                }
            }
        }
    }
}
