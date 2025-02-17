package com.example.intermodular.ui.screens.factEmitidas.info

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
import androidx.compose.material3.DropdownMenu
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
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel


/**
 * FORMULARIO CONFIRMACION IMPORTE  DE FACTURA
 */
@Composable
fun FEmitidasInfo(viewModel: FEmitidasInfoViewModel, indicadorProgresoViewModel: IndicadorProgresoViewModel) {

    val moneyCharge by viewModel.moneyCharge.observeAsState("")
    val totalAccount by viewModel.totalAccount.observeAsState("0.00") // q empiece en 0
    val taxType by viewModel.taxType.observeAsState(0.21)//asignams vlor por defecto

    //me faltan variables jiji

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //las llamadas al resto de funciones las haremos en FuncionesLogin
            FuncionesFEmitidasInfo(
                viewModel = viewModel,
                moneyCharge = moneyCharge,
                totalAccount = totalAccount,
                indicadorProgresoViewModel = indicadorProgresoViewModel
            )
        }
    }
}


@Composable
fun CardForm2(moneyCharge: String, totalAccount: String, taxType: String, indicadorProgresoViewModel: IndicadorProgresoViewModel){
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
                text = "Money charge",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = moneyCharge,
                onValueChange = { /* = it*/ },
                singleLine = true,
                label = { Text("000000") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Total amount",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = totalAccount,
                onValueChange = { /*nif = it*/ },
                singleLine = true,
                label = { Text("00000000P") },
                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(
                value = totalAccount,
                onValueChange = { /*viewModel.updateTotalAccount(it)*/ },
                singleLine = true,
                label = { Text("Total amount") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Tax Type", modifier = Modifier.fillMaxWidth())
            DropdownMenu(expanded = true, onDismissRequest = { }) {
                taxOptions.forEach { tax ->
                    DropdownMenuItem(onClick = {
                        selectedTax = tax
                        viewModel.updateTaxType(tax)
                    }) {
                        Text(text = tax)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))


            Button(
                onClick = {

                    /**aqui llamar a la funcion del indicador de progreso para avanzar*/
                    indicadorProgresoViewModel.avanzarPaso(3)
                },
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
fun FuncionesFEmitidasInfo(
    viewModel: FEmitidasInfoViewModel,
    moneyCharge: String,
    totalAccount: String,
    indicadorProgresoViewModel: IndicadorProgresoViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ){
            IndicadorProgreso(
                viewModel = indicadorProgresoViewModel,
                pasosTotales = 3
            )
    }
}