import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.intermodular.componentes.barraNavegacion.BottomNavigationBarComponent
import com.example.intermodular.ui.screens.home.HomeViewModel
import androidx.compose.ui.graphics.Brush
/*
@Composable
fun Home(viewModel: HomeViewModel, navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ProfileCard(navController)
            }
        }
    }
}

@Composable
fun ProfileCard(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("Perfil") }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF6F95AC)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Ver Perfil",
                fontSize = 18.sp,
                color = Color(0xFF6F95AC)
            )
        }
    }
}*/
/*
@Composable
fun Home(viewModel: HomeViewModel, navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Card for Profile
                ProfileCard(navController)

                // Displaying the total amount for Facturas
                TotalBox(title = "Total Facturas", amount = viewModel.totalFacturas.value ?: 0.0)

                Spacer(modifier = Modifier.height(8.dp))

                // Displaying the total amount for FacturasRe
                TotalBox(title = "Total FacturasRe", amount = viewModel.totalFacturasRe.value ?: 0.0)

                Spacer(modifier = Modifier.height(8.dp))

                // Displaying the difference between Facturas and FacturasRe
                TotalBox(title = "Diferencia Facturas - FacturasRe", amount = viewModel.totalResta.value ?: 0.0)
            }
        }
    }
}

@Composable
fun TotalBox(title: String, amount: Double) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE3F2FD)) // Light blue background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 16.sp, color = Color(0xFF0D47A1))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${"%.2f".format(amount)}", fontSize = 24.sp, color = Color(0xFF0D47A1))
        }
    }
}
@Composable
fun ProfileCard(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("Perfil") }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF6F95AC)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Ver Perfil",
                fontSize = 18.sp,
                color = Color(0xFF6F95AC)
            )
        }
    }
}*/



@Composable
fun Home(viewModel: HomeViewModel, navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBarComponent(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp)) // Espacio más grande arriba

                // Card for Profile
                ProfileCard(navController)

                Spacer(modifier = Modifier.height(16.dp))

                // Título y texto antes de los TotalBox
                Text(
                    text = "Bienvenido a tu aplicación de facturas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Aquí podrás gestionar y realizar un seguimiento de todas tus facturas de manera fácil y eficiente.",
                    fontSize = 14.sp,
                    color = Color(0xFF0D47A1)
                )

                Spacer(modifier = Modifier.height(24.dp)) // Más espacio entre los textos y las tarjetas

                // Displaying the total amount for Facturas
                TotalBox(title = "Total de facturas salientes", amount = viewModel.totalFacturas.value ?: 0.0)

                Spacer(modifier = Modifier.height(8.dp))

                // Displaying the total amount for Facturas received
                TotalBox(title = "Total de facturas entrantes", amount = viewModel.totalFacturasRe.value ?: 0.0)

                Spacer(modifier = Modifier.height(8.dp))

                // Displaying the difference between Facturas and FacturasRe
                TotalBox(title = "Diferencia final actual", amount = viewModel.totalResta.value ?: 0.0)

            }
        }
    }
}

@Composable
fun TotalBox(title: String, amount: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFEEEEEE), Color(0xFFDADADA))
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$${"%.2f".format(amount)}",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF008080)
                )
            }
        }
    }
}

@Composable
fun ProfileCard(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("Perfil") }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF6F95AC)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Ver Perfil",
                fontSize = 18.sp,
                color = Color(0xFF008080)
            )
        }
    }
}
