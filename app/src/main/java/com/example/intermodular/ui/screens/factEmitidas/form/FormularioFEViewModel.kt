package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intermodular.data.ConexionBaseDatos
import kotlinx.coroutines.launch

class FormularioFEViewModel: ViewModel() {

    // Variables del ViewModel
    private val _companiaNombre = MutableLiveData<String>()
    val companiaNombre: LiveData<String> = _companiaNombre

    private val _nif = MutableLiveData<String>()
    val nif: LiveData<String> = _nif

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    private val _nifError = MutableLiveData<String?>()
    val nifError: LiveData<String?> = _nifError

    private val _guardadoExitoso = MutableLiveData<Boolean>()
    val guardadoExitoso: LiveData<Boolean> = _guardadoExitoso

    // Función para actualizar el NIF
    fun actualizarNIF(nuevoNIF: String) {
        if (validarNIF(nuevoNIF)) {
            _nif.value = nuevoNIF
            _nifError.value = null
        } else {
            _nifError.value = "NIF inválido"
        }
    }

    // Validación de NIF
    fun validarNIF(nif: String): Boolean {
        val regexDNI = Regex("^[0-9]{8}[A-Za-z]$")
        val regexNIE = Regex("^[XYZ][0-9]{7}[A-Za-z]$")

        if (!nif.matches(regexDNI) && !nif.matches(regexNIE)) {
            return false
        }

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

    // Función para guardar la factura emitida
    fun guardarFacturaEmitida() {
        val companiaNombre = _companiaNombre.value ?: ""
        val nif = _nif.value ?: ""
        val direccion = _direccion.value ?: ""

        viewModelScope.launch {
            try {
                ConexionBaseDatos.conexionBaseDatos.collection("facturas")
                    .add(
                        hashMapOf(
                            "companiaNombre" to companiaNombre,
                            "nif" to nif,
                            "direccion" to direccion
                        )
                    ).addOnSuccessListener {
                        _guardadoExitoso.value = true
                        _companiaNombre.value = ""
                        _nif.value = ""
                        _direccion.value = ""
                        println("Factura guardada exitosamente")
                    }.addOnFailureListener {
                        _guardadoExitoso.value = false
                        println("Error al guardar la factura: ${it.message}")
                    }
            } catch (e: Exception) {
                _guardadoExitoso.value = false
                println("Error: ${e.message}")
            }
        }
    }

    fun actualizarCompaniaNombre(nuevoNombre: String) {
        _companiaNombre.value = nuevoNombre
    }

    fun actualizarNif(nuevoNif: String) {
        _nif.value = nuevoNif
    }

    fun actualizarDireccion(nuevaDireccion: String) {
        _direccion.value = nuevaDireccion
    }
}
