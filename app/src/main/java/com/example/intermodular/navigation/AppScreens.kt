package com.example.intermodular.navigation

sealed class AppScreens(val ruta: String){
    object Home: AppScreens("Home")
    object Login: AppScreens("Login")
    object Registro: AppScreens("Registro")
    object FEmitidasInfo: AppScreens("FEmitidasInfo")
    object FormularioFE: AppScreens("FormularioFE")

    object IndicadorProgreso: AppScreens("IndicadorProgreso")
}
