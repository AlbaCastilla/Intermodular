package com.example.intermodular.ui.screens.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun Registro(viewModel: RegistroViewModel) {
//    var companyName by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var nif by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Card(
@Composable
fun Registro(viewModel: RegistroViewModel) {
    var companyName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nif by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)), // Fondo gris claro sutil
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp) // Más margen para un mejor centrado
        ) {
            Text(
                text = "Complete the following fields",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp) // Más separación con el formulario
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp), // Sombra sutil para resaltar el formulario
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color(0xFFF5F5F5)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = "Please complete the following fields on the invoice receiver's information",
//                    fontSize = 12.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.Gray
//                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Company/personal full name",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    singleLine = true,
                    label = { Text("Company/personal full name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Company/personal address",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    singleLine = true,
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Company/personal email",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Company/personal NIF",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = nif,
                    onValueChange = { nif = it },
                    singleLine = true,
                    label = { Text("NIF") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Chose a password",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Confirm Password",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    singleLine = true,
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {/*todo*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Register",
                        fontSize = 16.sp,
                    )
                }
            }
        }}}}

