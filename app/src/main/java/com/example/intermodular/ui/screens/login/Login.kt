package com.example.intermodular.ui.screens.login

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(viewModel: LoginViewModel, navController: NavHostController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password = viewModel.password.observeAsState(initial = "").value ?: ""
    val loginResult: Boolean? by viewModel.loginResult.observeAsState()
    val userId: String? by viewModel.userId.observeAsState()

    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser

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
                    .background(Color(0xFFF5F5F5))
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Enter your email and password",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(text = "Email", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                            OutlinedTextField(
                                value = email,
                                onValueChange = { viewModel.actualizarEmail(it) },
                                singleLine = true,
                                label = { Text("Email") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(text = "Password", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                            OutlinedTextField(
                                value = password,
                                onValueChange = { viewModel.actualizarPassword(it) },
                                singleLine = true,
                                label = { Text("Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = { viewModel.iniciarSesion()
                                    navController.navigate(AppScreens.FormularioFE.ruta)
                                    viewModel.guardarDatosUsuario(context, user)},
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Login", fontSize = 16.sp)
                            }

                            // Show login result message
                            loginResult?.let {
                                if (it) {
                                    Text(
                                        text = "Logged in successfully! Email: $email, User ID: $userId",
                                        color = Color.Green,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(top = 16.dp)
                                    )
                                } else {
                                    Text(
                                        text = "Login failed! ${viewModel.errorMessage.value}",
                                        color = Color.Red,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(top = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

