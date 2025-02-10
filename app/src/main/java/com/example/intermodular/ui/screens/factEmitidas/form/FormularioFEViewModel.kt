package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormularioFEViewModel: ViewModel() {

    //VARIABLES DEL VIEWMODEL
    private val _companiaNombre = MutableLiveData<String>()
    val companiaNombre: LiveData<String> = _companiaNombre

    private val _nif = MutableLiveData<String>()
    val nif: LiveData<String> = _nif

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    //para la actualizacion del estado --> q sera visible en el formulario
    private val _nifError = MutableLiveData<String?>()
    val nifError: LiveData<String?> = _nifError

    //para actualizar el estado del nif --> si sigue siendo valido o no
    fun actualizarNIF(nuevoNIF: String) {
        if (validarNIF(nuevoNIF)) {
            _nif.value = nuevoNIF
            _nifError.value = null  // Borra el error si es válido
        } else {
            _nifError.value = "NIF inválido"
        }
    }

    //PARA LA VALIDACION DEL NIF
    fun validarNIF(nif: String): Boolean {
        val regexDNI = Regex("^[0-9]{8}[A-Za-z]$")
        val regexNIE = Regex("^[XYZ][0-9]{7}[A-Za-z]$")

        if (!nif.matches(regexDNI) && !nif.matches(regexNIE)) {
            return false
        }

        // Convertir NIE a DNI para calcular la letra
        val nieReemplazado = when (nif.first()) {
            'X' -> nif.replaceFirst("X", "0")
            'Y' -> nif.replaceFirst("Y", "1")
            'Z' -> nif.replaceFirst("Z", "2")
            else -> nif
        }

        val numeros = nieReemplazado.dropLast(1).toInt()
        val letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE"[numeros % 23]
        val letraIngresada = nif.last().uppercaseChar()

        return letraCalculada == letraIngresada
    }
}