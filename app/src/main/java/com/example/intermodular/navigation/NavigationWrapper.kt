package com.example.intermodular.navigation

import FEHomeViewModel
import FormularioConProgreso
import Home
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgreso
import com.example.intermodular.componentes.indicadorProgreso.IndicadorProgresoViewModel
import com.example.intermodular.ui.screens.Perfil.Perfil
import com.example.intermodular.ui.screens.Perfil.PerfilViewModel
import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFE

import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFEViewModel
//import com.example.intermodular.ui.screens.factEmitidas.form.FormularioFEViewModel2
import com.example.intermodular.ui.screens.factEmitidas.form.FuncionesFormularioFE3
import com.example.intermodular.ui.screens.factEmitidas.screen.FEHome
import com.example.intermodular.ui.screens.factRecibidas.form.FormularioConProgreso2
import com.example.intermodular.ui.screens.factRecibidas.form.FormularioFR
import com.example.intermodular.ui.screens.factRecibidas.form.FormularioFRViewModel
import com.example.intermodular.ui.screens.factRecibidas.form.FuncionesFormularioFR3
import com.example.intermodular.ui.screens.factRecibidas.screen.FRHome
import com.example.intermodular.ui.screens.factRecibidas.screen.FRHomeViewModel


import com.example.intermodular.ui.screens.home.HomeViewModel
import com.example.intermodular.ui.screens.login.Login
import com.example.intermodular.ui.screens.login.LoginViewModel
import com.example.intermodular.ui.screens.registro.Registro
import com.example.intermodular.ui.screens.registro.RegistroViewModel


@Composable
fun NavigationWrapper (navController: NavHostController) {

    //variables para los viewModel
    val homeViewModel: HomeViewModel = viewModel()
    val perfilViewModel: PerfilViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()
    val registroViewModel: RegistroViewModel = viewModel()
    val formularioFEViewModel: FormularioFEViewModel = viewModel()
    val indicadorProgresoViewModel: IndicadorProgresoViewModel = viewModel()
    val formularioFRViewModel: FormularioFRViewModel = viewModel()
    val frHomeViewModel: FRHomeViewModel = viewModel()
    val feHomeViewModel: FEHomeViewModel = viewModel()



    NavHost(navController = navController, startDestination = AppScreens.Registro.ruta) {
        composable(AppScreens.Home.ruta){
            Home(
                viewModel = homeViewModel,
                navController = navController // ← Pasa el NavController correcto
            )
        }
        composable(AppScreens.Perfil.ruta){
            Perfil(
                viewModel = perfilViewModel,
                navController = navController
            )
        }

        composable(AppScreens.Login.ruta){
            Login(
                viewModel = loginViewModel,
                navController = navController
            )
        }
        composable(AppScreens.Registro.ruta){
            Registro(
                viewModel = registroViewModel
                //luego añadiremos el navController q sino da error :)
            )
        }


        //FACTURAS EMITIDAS - formulario
        composable(AppScreens.FormularioFE.ruta){
            FormularioFE(
                viewModel = formularioFEViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel ,
                navController = navController
            )
        }
        composable(AppScreens.FormularioFE3.ruta){
            FuncionesFormularioFE3(
                viewModel = formularioFEViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel ,
                navController = navController
            )
        }
        composable(AppScreens.FormularioFE2.ruta){
            FormularioConProgreso(
                viewModel = formularioFEViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel,
                navController = navController
            )
        }

        composable(AppScreens.IndicadorProgreso.ruta){
            IndicadorProgreso(
                viewModel = indicadorProgresoViewModel
            )
        }

        composable(AppScreens.FormularioFR.ruta){
            FormularioFR(
                viewModel = formularioFRViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel,
                navController = navController
            )
        }

        composable(AppScreens.FormularioFR2.ruta){
            FormularioConProgreso2(
                viewModel = formularioFRViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel,
                navController = navController
            )
        }

        composable(AppScreens.FormularioFR3.ruta){
            FuncionesFormularioFR3(
                viewModel = formularioFRViewModel,
                indicadorProgresoViewModel = indicadorProgresoViewModel,
                navController = navController
            )
        }

        composable(AppScreens.FEHome.ruta) {
            FEHome(
                viewModel = feHomeViewModel,
                navController = navController
            )
        }

        composable(AppScreens.FRHome.ruta) {
            FRHome(
                viewModel = frHomeViewModel,
                navController = navController
            )
        }

    }
}


