package com.example.intermodular.ui.screens.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.models.User
import com.example.intermodular.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(viewModel: RegistroViewModel, navController: NavHostController) {
    val companyName: String by viewModel.companyName.observeAsState(initial = "")
    val address: String by viewModel.address.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val nif: String? by viewModel.nif.observeAsState(initial = "")
    val password: String? by viewModel.password.observeAsState(initial = "")
    val confirmPassword: String? by viewModel.confirmPassword.observeAsState(initial = "")

    val companyNameError: String? by viewModel.companyNameError.observeAsState()
    val addressError: String? by viewModel.addressError.observeAsState()
    val emailError: String? by viewModel.emailError.observeAsState()
    val nifError: String? by viewModel.nifError.observeAsState()
    val passwordError: String? by viewModel.passwordError.observeAsState()
    val confirmPasswordError: String? by viewModel.confirmPasswordError.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Register",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(
                    0xFF577A8E
                ))
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()) // Enable scrolling
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Complete the following fields",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(), // Adjusts to content
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Company/personal full name",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            OutlinedTextField(
                                value = companyName,
                                onValueChange = { viewModel.actualizarCompanyName(it)
                                    viewModel.checkErrorState() },
                                singleLine = true,
                                label = { Text("Company/personal full name") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF577A8E),
                                    unfocusedBorderColor = Color(0xFF577A8E),
                                    cursorColor = Color(0xFF577A8E),
                                    focusedLabelColor = Color(0xFF577A8E),
                                )
                            )
                            companyNameError?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Company/personal address",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            OutlinedTextField(
                                value = address,
                                onValueChange = { viewModel.actualizarAddress(it)
                                    viewModel.checkErrorState()},
                                singleLine = true,
                                label = { Text("Address") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF577A8E),
                                    unfocusedBorderColor = Color(0xFF577A8E),
                                    cursorColor = Color(0xFF577A8E),
                                    focusedLabelColor = Color(0xFF577A8E),
                                )
                            )
                            addressError?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Company/personal email",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            OutlinedTextField(
                                value = email,
                                onValueChange = { viewModel.actualizarEmail(it)
                                    viewModel.checkErrorState()},
                                singleLine = true,
                                label = { Text("Email") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF577A8E),
                                    unfocusedBorderColor = Color(0xFF577A8E),
                                    cursorColor = Color(0xFF577A8E),
                                    focusedLabelColor = Color(0xFF577A8E),
                                )
                            )
                            emailError?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Company/personal TIN",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            OutlinedTextField(
                                value = nif ?: "",
                                onValueChange = { viewModel.actualizarNif(it)
                                    viewModel.checkErrorState()},
                                singleLine = true,
                                label = { Text("NIF") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF577A8E),
                                    unfocusedBorderColor = Color(0xFF577A8E),
                                    cursorColor = Color(0xFF577A8E),
                                    focusedLabelColor = Color(0xFF577A8E),
                                )
                            )
                            nifError?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Choose a password",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            OutlinedTextField(
                                value = password ?: "",
                                onValueChange = { viewModel.actualizarPassword(it)
                                    viewModel.checkErrorState()},
                                singleLine = true,
                                label = { Text("Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF577A8E),
                                    unfocusedBorderColor = Color(0xFF577A8E),
                                    cursorColor = Color(0xFF577A8E),
                                    focusedLabelColor = Color(0xFF577A8E),
                                )
                            )
                            passwordError?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Confirm Password",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            OutlinedTextField(
                                value = confirmPassword ?: "",
                                onValueChange = { viewModel.actualizarConfirmPassword(it)
                                    viewModel.checkErrorState()},
                                singleLine = true,
                                label = { Text("Confirm Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color(0xFF577A8E),
                                    unfocusedBorderColor = Color(0xFF577A8E),
                                    cursorColor = Color(0xFF577A8E),
                                    focusedLabelColor = Color(0xFF577A8E),
                                )
                            )
                            confirmPasswordError?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            val isFieldsFilled by viewModel.isFieldsFilled.observeAsState(initial = false)

                            Button(
                                onClick = {
                                    // Validate the form when the button is clicked
                                    viewModel.validateForm()

                                    // Register user if the form is valid
                                    if (viewModel.isFormValid.value == true) {
                                        viewModel.registrarUsuario()
                                    }
                                },
                                enabled = isFieldsFilled,  // Button enabled only when all fields are filled
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF577A8E) // Button color
                                ),
                            ) {
                                Text("Register")
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Already have an account? Log in",
                                color = Color(0xFF577A8E),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(AppScreens.Login.ruta)
                                    }
                                    .padding(top = 8.dp),
                                textAlign = TextAlign.Center
                            )

                        }   }
                }
            }
        }
    )
}
