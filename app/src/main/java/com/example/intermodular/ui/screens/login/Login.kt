package com.example.intermodular.ui.screens.login

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(viewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)) // Fondo gris claro sutil
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp) // Más margen para un mejor centrado
                ) {
                    Text(
                        text = "Enter your email and password",
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
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Email",
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
                                text = "Password",
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

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = {/*todo*/},
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Login",
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
