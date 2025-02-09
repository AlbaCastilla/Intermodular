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
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@Composable
fun FormularioFE(viewModel: FormularioFEViewModel) {

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

            Text(text = "Iniciar sesión")

            //las llamadas al resto de funciones las haremos en FuncionesLogin
            FuncionesFormularioFE(
                viewModel = viewModel,
                companiaNombre = companiaNombre,
                nif = nif,
                direccion = direccion
            )
        }
    }

}

@Composable
fun CardForm(companiaNombre: String, nif: String, direccion: String){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
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
                onValueChange = { /* = it*/ },
                singleLine = true,
                label = { Text("Jane Doe") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "NIF",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = nif,
                onValueChange = { /*nif = it*/ },
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
                onValueChange = { /*address = it*/ },
                singleLine = true,
                label = { Text("xxxxxx") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* Acción del botón */ },
                modifier = Modifier.fillMaxWidth(),
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
        }
    }
}


@Composable
fun FuncionesFormularioFE(
    viewModel: FormularioFEViewModel,
    companiaNombre: String,
    nif: String,
    direccion: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CardForm(
            companiaNombre = companiaNombre,
            nif = nif,
            direccion = direccion
        )
    }
}