package com.example.intermodular.ui.screens.Perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.barraNavegacion.BottomNavigationBarComponent
import com.example.intermodular.ui.screens.home.HomeViewModel

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(viewModel: PerfilViewModel, navController: NavHostController) {
    val companyName by viewModel.companyName.observeAsState("")
    val address by viewModel.address.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val nif by viewModel.nif.observeAsState("")
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.cargarDatosUsuario(context)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
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
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        imageVector = Icons.Filled.AccountCircle, // Este es el ícono de un perfil
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        tint = MaterialTheme.colorScheme.primary // Puedes cambiar el color del ícono si lo deseas
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Input para el Company Name
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { /**/ },
                        label = { Text("Company Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Input para el Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { /**/ },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Input para el Address
                    OutlinedTextField(
                        value = address,
                        onValueChange = { /**/ },
                        label = { Text("Address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Input para el NIF
                    OutlinedTextField(
                        value = nif,
                        onValueChange = { /**/ },
                        label = { Text("NIF") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { /* Save profile action */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Profile")
                    }
                }
            }
        }
    )
}*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(viewModel: PerfilViewModel, navController: NavHostController) {
    // Observe LiveData from ViewModel
    val companyName by viewModel.companyName.observeAsState("")
    val address by viewModel.address.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val nif by viewModel.nif.observeAsState("")
    val context = LocalContext.current

    // Load user data once when the composable is first displayed
    LaunchedEffect(Unit) {
        viewModel.cargarDatosUsuario(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
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
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Bind each input to the corresponding LiveData value
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { viewModel._companyName.value = it },
                        label = { Text("Company Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { viewModel._email.value = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = address,
                        onValueChange = { viewModel._address.value = it },
                        label = { Text("Address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nif,
                        onValueChange = { viewModel._nif.value = it },
                        label = { Text("NIF") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    /*Button(
                        onClick = { /* Save profile action */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Profile")
                    }*/
                    Button(
                        onClick = {
                            viewModel.guardarDatosUsuario(context)  // Save locally
                            viewModel.actualizarUsuarioEnFirebase(context)  // Sync with Firestore
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Profile")
                    }


                }
            }
        }
    )
}*/





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(viewModel: PerfilViewModel, navController: NavHostController) {
    val companyName by viewModel.companyName.observeAsState("")
    val address by viewModel.address.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val nif by viewModel.nif.observeAsState("")
    val context = LocalContext.current
    val userUpdated by viewModel.userUpdated.observeAsState(false)

    // Load user data once when the composable is first displayed
    LaunchedEffect(Unit) {
        viewModel.cargarDatosUsuario(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(
                    0xFF577A8E
                )/*MaterialTheme.colorScheme.primary*/)
            )
        },
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
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
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, /*MaterialTheme.colorScheme.primary*/Color.LightGray, CircleShape),
                        tint = Color.LightGray//MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                     // Email solo de lectura
                    Text(
                        text = email,
                        color = Color(0xFF757575),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White, RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo para Company Name
                    CustomTextField(
                        value = companyName,
                        label = "Company Name",
                        onValueChange = { viewModel._companyName.value = it }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo para Address
                    CustomTextField(
                        value = address,
                        label = "Address",
                        onValueChange = { viewModel._address.value = it }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo para NIF/DNI
                    CustomTextField(
                        value = nif,
                        label = "NIF/DNI",
                        onValueChange = { viewModel._nif.value = it }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            viewModel.guardarDatosUsuario(context)
                            viewModel.actualizarUsuarioEnFirebase(context)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6FA0A8))
                    ) {
                        Text("Update Profile")
                    }

                    // Mostrar Snackbar si el usuario se actualizó con éxito
                    if (userUpdated) {
                        Snackbar(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("User updated successfully!")
                        }
                    }

                    Button(
                        onClick = {
                            // Llamar a la función logout
                            viewModel.logout(context)

                            // Redirigir al login después de cerrar sesión
                            navController.navigate("Login")  // Reemplaza "login_route" con la ruta correcta de tu login
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                    ) {
                        Text("Logout")
                    }

                }
            }
        }
    )
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String, label: String, onValueChange: (String) -> Unit) {
    val borderColor = if (value.isBlank()) Color.Red else Color(0xFF6200EA)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        isError = value.isBlank(),
        trailingIcon = {
            if (value.isNotBlank()) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Valid",
                    tint = Color.Green
                )
            }
        },
        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = Color(0xFFDDDDDD),
            cursorColor = Color(0xFF6200EA)
        )
    )
}
