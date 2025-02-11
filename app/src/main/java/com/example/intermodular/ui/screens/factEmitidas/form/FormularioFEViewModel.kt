package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

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

    // CREAMOS UNA FUNCIÓN PARA GENERAR LA CONEXION A LA BASE DE DATOS Y GUARDAR LOS DATOS DE LA FACTURA EMITIDA
    fun guardarFacturaEmitida(companiaNombre: String, nif: String, direccion: String) {
        val db = FirebaseFirestore.getInstance()
        val coleccion = "facturas"

        // Tomamos los valores actuales de LiveData
        val companiaNombre = _companiaNombre.value ?: ""
        val nif = _nif.value ?: ""
        val direccion = _direccion.value ?: ""

        db.collection(coleccion)
            .add(
                hashMapOf(
                    "companiaNombre" to companiaNombre,
                    "nif" to nif,
                    "direccion" to direccion
                )
            )
            .addOnSuccessListener {
                // Limpiar los campos después del guardado
                _companiaNombre.value = ""
                _nif.value = ""
                _direccion.value = ""
                println("Se han guardado los datos bien")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
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