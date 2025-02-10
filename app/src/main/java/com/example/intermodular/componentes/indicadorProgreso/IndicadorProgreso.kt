package com.example.intermodular.componentes.indicadorProgreso

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.intermodular.navigation.AppScreens


@Composable
fun IndicadorProgreso(viewModel: IndicadorProgresoViewModel, pasosTotales: Int = 3) {
    //var pasoActual by remember { mutableStateOf(0) }
    val pasoActual by viewModel.pasoActual.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Línea de progreso con bolitas y líneas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            for (i in 0 until pasosTotales) {
                // Dibuja el círculo del paso
                StepCircle(isActive = i <= pasoActual)

                if (i < pasosTotales - 1) {
                    StepLine(isCompleted = i < pasoActual, modifier = Modifier.weight(1f))
                }
            }
        }

        //BTN SIGUIENTE --> lo estoy pasando a los btns del propio formulario
//        Button(
//            onClick = { viewModel.avanzarPaso(pasosTotales) }
//        ) {
//            Text("Siguiente")
//        }

        //Spacer(modifier = Modifier.height(16.dp))

        //BTN ANTERIOR--> idem
//        Button(
//            onClick = {
//                viewModel.retrocederPaso()
//            }
//        ) {
//            Text("Anterior")
//        }
    }
}

@Composable
fun StepCircle(isActive: Boolean) {
    Canvas(
        modifier = Modifier
            .size(32.dp)
            .padding(4.dp)

    ) {
        drawCircle(
            color = if (isActive) Color(0xFF4A6583) else Color.LightGray, //q cambie el color
            radius = size.minDimension / 2
        )
    }
}

@Composable
fun StepLine(isCompleted: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(2.dp)
            .background(if (isCompleted) Color(0xFF4A6583) else Color.LightGray)
    )
}