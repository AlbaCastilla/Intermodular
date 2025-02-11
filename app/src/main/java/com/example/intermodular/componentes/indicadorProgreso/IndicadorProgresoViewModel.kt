package com.example.intermodular.componentes.indicadorProgreso

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IndicadorProgresoViewModel: ViewModel() {
    private val _pasoActual = MutableStateFlow(0)
    val pasoActual: StateFlow<Int> = _pasoActual

    fun avanzarPaso(totalPasos: Int) {
        if (_pasoActual.value < totalPasos - 1) {
            _pasoActual.value++
        }
    }

    fun retrocederPaso() {
        if (_pasoActual.value > 0) {
            _pasoActual.value--
        }
    }
}