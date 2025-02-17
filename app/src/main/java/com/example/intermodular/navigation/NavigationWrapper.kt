package com.example.intermodular.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel
import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFE
import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFEViewModel
import com.example.intermodular.ui.screens.factEmitidas.info.FEmitidasInfo
import com.example.intermodular.ui.screens.factEmitidas.info.FEmitidasInfoViewModel
import com.example.intermodular.ui.screens.home.Home
import com.example.intermodular.ui.screens.home.HomeViewModel
import com.example.intermodular.ui.screens.login.Login
import com.example.intermodular.ui.screens.login.LoginViewModel
import com.example.intermodular.ui.screens.registro.Registro
import com.example.intermodular.ui.screens.registro.RegistroViewModel

@Composable
fun NavigationWrapper (navController: NavHostController) {

    //variables para los viewModel
    val homeViewModel: HomeViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()
    val registroViewModel: RegistroViewModel = viewModel()
    val femitidasInfoViewModel: FEmitidasInfoViewModel = viewModel()
    val formularioFEViewModel: FormularioFEViewModel = viewModel()
    val indicadorProgresoViewModel: IndicadorProgresoViewModel = viewModel()


    NavHost(navController = navController, startDestination = AppScreens.Login.ruta) {
        composable(AppScreens.Home.ruta){
            Home(
                viewModel = homeViewModel
                //luego añadiremos el navController q sino da error :)
            )
        }
        composable(AppScreens.Login.ruta){
            Login(
                viewModel = loginViewModel
                //luego añadiremos el navController q sino da error :)
            )
        }
        composable(AppScreens.Registro.ruta){
            Registro(
                viewModel = registroViewModel
                //luego añadiremos el navController q sino da error :)
            )
        }

        //FACTURAS EMITIDAS - info
        composable(AppScreens.FEmitidasInfo.ruta){
            FEmitidasInfo(
                viewModel = femitidasInfoViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel
            )
        }

        //FACTURAS EMITIDAS - formulario
        composable(AppScreens.FormularioFE.ruta){
            FormularioFE(
                viewModel = formularioFEViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel
            )
        }

        composable(AppScreens.IndicadorProgreso.ruta){
            IndicadorProgreso(
                viewModel = indicadorProgresoViewModel
            )
        }

    }
}