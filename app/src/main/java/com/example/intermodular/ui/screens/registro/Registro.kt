package com.example.intermodular.ui.screens.registro

import androidx.compose.foundation.background
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(viewModel: RegistroViewModel) {
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
                title = { Text("Registro") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
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
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
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
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
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
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
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
                                text = "Company/personal NIF",
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
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
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
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
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
                                shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
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
//
//                            Button(
//                                onClick = { viewModel.registrarUsuario() },
//                                modifier = Modifier.fillMaxWidth(),
//                                shape = RoundedCornerShape(8.dp)
//                            ) {
//                                Text(
//                                    text = "Register",
//                                    fontSize = 16.sp,
//                                )
//                            }
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
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Register")
                            }

                        }   }
                }
            }
        }
    )
}

//package com.example.intermodular.ui.screens.registro
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material3.AlertDialogDefaults.containerColor
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
////@Composable
////fun Registro(viewModel: RegistroViewModel) {
////    var companyName by remember { mutableStateOf("") }
////    var address by remember { mutableStateOf("") }
////    var email by remember { mutableStateOf("") }
////    var nif by remember { mutableStateOf("") }
////    var password by remember { mutableStateOf("") }
////    var confirmPassword by remember { mutableStateOf("") }
////
////    Box(
////        modifier = Modifier.fillMaxSize(),
////        contentAlignment = Alignment.Center
////    ) {
////        Card(
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
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF5F5F5)), // Fondo gris claro sutil
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.padding(24.dp) // Más margen para un mejor centrado
//        ) {
//            Text(
//                text = "Complete the following fields",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                modifier = Modifier.padding(bottom = 12.dp) // Más separación con el formulario
//            )
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                elevation = CardDefaults.elevatedCardElevation(4.dp), // Sombra sutil para resaltar el formulario
//                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
//            ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .background(Color(0xFFF5F5F5)),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
////                Spacer(modifier = Modifier.height(8.dp))
////                Text(
////                    text = "Please complete the following fields on the invoice receiver's information",
////                    fontSize = 12.sp,
////                    textAlign = TextAlign.Center,
////                    color = Color.Gray
////                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Company/personal full name",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Start
//                )
//                OutlinedTextField(
//                    value = companyName,
//                    onValueChange = { companyName = it },
//                    singleLine = true,
//                    label = { Text("Company/personal full name") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Text(
//                    text = "Company/personal address",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Start
//                )
//                OutlinedTextField(
//                    value = address,
//                    onValueChange = { address = it },
//                    singleLine = true,
//                    label = { Text("Address") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Text(
//                    text = "Company/personal email",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Start
//                )
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { email = it },
//                    singleLine = true,
//                    label = { Text("Email") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Text(
//                    text = "Company/personal NIF",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Start
//                )
//                OutlinedTextField(
//                    value = nif,
//                    onValueChange = { nif = it },
//                    singleLine = true,
//                    label = { Text("NIF") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Text(
//                    text = "Chose a password",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Start
//                )
//                OutlinedTextField(
//                    value = password,
//                    onValueChange = { password = it },
//                    singleLine = true,
//                    label = { Text("Password") },
//                    visualTransformation = PasswordVisualTransformation(),
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Text(
//                    text = "Confirm Password",
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Start
//                )
//                OutlinedTextField(
//                    value = confirmPassword,
//                    onValueChange = { confirmPassword = it },
//                    singleLine = true,
//                    label = { Text("Confirm Password") },
//                    visualTransformation = PasswordVisualTransformation(),
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = with(MaterialTheme) { shapes.large.copy(all = CornerSize(16.dp)) }
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Button(
//                    onClick = {/*todo*/},
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = "Register",
//                        fontSize = 16.sp,
//                    )
//                }
//            }
//        }}}}
//
