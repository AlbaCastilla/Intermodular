package com.example.intermodular.navigation

sealed class AppScreens(val ruta: String){
    object Home: AppScreens("Home")
    object Login: AppScreens("Login")
    object Registro: AppScreens("Registro")
    object FormularioFE: AppScreens("FormularioFE")
    object FormularioFE2: AppScreens("FormularioFE2")
    object FormularioFE3: AppScreens("FormularioFE3")
    object FormularioFR: AppScreens("FormularioFR")
    object FormularioFR2: AppScreens("FormularioFR2")
    object FormularioFR3: AppScreens("FormularioFR3")
    object FEHome: AppScreens("FEHome")


    object IndicadorProgreso: AppScreens("IndicadorProgreso")
}
