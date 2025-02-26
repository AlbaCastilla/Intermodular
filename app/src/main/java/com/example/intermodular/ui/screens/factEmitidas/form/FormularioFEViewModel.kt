package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intermodular.models.Cliente
import com.example.intermodular.models.Factura
import com.google.firebase.firestore.FirebaseFirestore

///*
//class FormularioFEViewModel: ViewModel() {
//
//    //VARIABLES DEL VIEWMODEL
//    private val _companiaNombre = MutableLiveData<String>()
//    val companiaNombre: LiveData<String> = _companiaNombre
//
//    private val _nif = MutableLiveData<String>()
//    val nif: LiveData<String> = _nif
//
//    private val _direccion = MutableLiveData<String>()
//    val direccion: LiveData<String> = _direccion
//
//    //para la actualizacion del estado --> q sera visible en el formulario
//    private val _nifError = MutableLiveData<String?>()
//    val nifError: LiveData<String?> = _nifError
//
//    //para actualizar el estado del nif --> si sigue siendo valido o no
//    fun actualizarNIF(nuevoNIF: String) {
//        if (validarNIF(nuevoNIF)) {
//            _nif.value = nuevoNIF
//            _nifError.value = null  // Borra el error si es válido
//        } else {
//            _nifError.value = "NIF inválido"
//        }
//    }
//
//    //PARA LA VALIDACION DEL NIF
//    fun validarNIF(nif: String): Boolean {
//        val regexDNI = Regex("^[0-9]{8}[A-Za-z]$")
//        val regexNIE = Regex("^[XYZ][0-9]{7}[A-Za-z]$")
//
//        if (!nif.matches(regexDNI) && !nif.matches(regexNIE)) {
//            return false
//        }
//
//        // Convertir NIE a DNI para calcular la letra
//        val nieReemplazado = when (nif.first()) {
//            'X' -> nif.replaceFirst("X", "0")
//            'Y' -> nif.replaceFirst("Y", "1")
//            'Z' -> nif.replaceFirst("Z", "2")
//            else -> nif
//        }
//
//        val numeros = nieReemplazado.dropLast(1).toInt()
//        val letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE"[numeros % 23]
//        val letraIngresada = nif.last().uppercaseChar()
//
//        return letraCalculada == letraIngresada
//    }
//
//    // **VARIABLE PARA ALMACENAR EL ID DEL CLIENTE GUARDADO**
//    private val _clienteId = MutableLiveData<String?>()
//    val clienteId: LiveData<String?> = _clienteId
//
//    // CREAMOS UNA FUNCIÓN PARA GENERAR LA CONEXION A LA BASE DE DATOS Y GUARDAR LOS DATOS DE LA FACTURA EMITIDA
//    /*fun guardarFacturaEmitida(nombre: String, nif: String, direccion: String) {
//        val db = FirebaseFirestore.getInstance()
//        val coleccion = "clientes"
//
//        // Tomamos los valores actuales de LiveData
//        val nombre = _companiaNombre.value ?: ""
//        val nif = _nif.value ?: ""
//        val direccion = _direccion.value ?: ""
//
//        db.collection(coleccion)
//            .add(
//                hashMapOf(
//                    "nombre" to nombre,
//                    "nif" to nif,
//                    "direccion" to direccion
//                )
//            )
//            .addOnSuccessListener {
//                // Limpiar los campos después del guardado
//                _companiaNombre.value = ""
//                _nif.value = ""
//                _direccion.value = ""
//                println("Se han guardado los datos bien")
//            }
//            .addOnFailureListener { e ->
//                println("Error adding document: $e")
//            }
//    }*/
//    fun guardarFacturaEmitida(nombre: String, nif: String, direccion: String) {
//        val db = FirebaseFirestore.getInstance()
//        val coleccion = "clientes"
//
//        // Tomamos los valores actuales de LiveData
//        val nombre = _companiaNombre.value ?: ""
//        val nif = _nif.value ?: ""
//        val direccion = _direccion.value ?: ""
//
//        // Creamos un nuevo documento con un ID automático
//        val nuevoDocumento = db.collection(coleccion).document()
//
//        val cliente = Cliente(
//            id = nuevoDocumento.id,
//            nombre = nombre,
//            nif = nif,
//            direccion = direccion
//        )
//
//        nuevoDocumento.set(cliente)
//            .addOnSuccessListener {
//                // Guardamos el ID en la variable local para usarlo en otra vista
//                _clienteId.value = cliente.id
//
//                // Limpiamos los campos después del guardado
//                _companiaNombre.value = ""
//                _nif.value = ""
//                _direccion.value = ""
//
//                println("Se han guardado los datos correctamente. ID: ${cliente.id}")
//            }
//            .addOnFailureListener { e ->
//                println("Error al agregar documento: $e")
//            }
//    }
//
//
//    fun actualizarCompaniaNombre(nuevoNombre: String) {
//        _companiaNombre.value = nuevoNombre
//    }
//
//    fun actualizarNif(nuevoNif: String) {
//        _nif.value = nuevoNif
//    }
//
//    fun actualizarDireccion(nuevaDireccion: String) {
//        _direccion.value = nuevaDireccion
//    }
//
//
//}*/
//class FormularioFEViewModel : ViewModel() {
//
//    private val _companiaNombre = MutableLiveData<String>()
//    val companiaNombre: LiveData<String> = _companiaNombre
//
//    private val _nif = MutableLiveData<String>()
//    val nif: LiveData<String> = _nif
//
//    private val _direccion = MutableLiveData<String>()
//    val direccion: LiveData<String> = _direccion
//
//    private val _nifError = MutableLiveData<String?>()
//    val nifError: LiveData<String?> = _nifError
//
//    private val _clienteId = MutableLiveData<String?>()
//    val clienteId: LiveData<String?> = _clienteId
//
//    // ** Variable local para almacenar el clienteId **
//    private var clienteIdLocal: String? = null
//
//    fun actualizarNIF(nuevoNIF: String) {
//        if (validarNIF(nuevoNIF)) {
//            _nif.value = nuevoNIF
//            _nifError.value = null
//        } else {
//            _nifError.value = "NIF inválido"
//        }
//    }
//
//    fun validarNIF(nif: String): Boolean {
//        val regexDNI = Regex("^[0-9]{8}[A-Za-z]$")
//        val regexNIE = Regex("^[XYZ][0-9]{7}[A-Za-z]$")
//
//        if (!nif.matches(regexDNI) && !nif.matches(regexNIE)) {
//            return false
//        }
//
//        val nieReemplazado = when (nif.first()) {
//            'X' -> nif.replaceFirst("X", "0")
//            'Y' -> nif.replaceFirst("Y", "1")
//            'Z' -> nif.replaceFirst("Z", "2")
//            else -> nif
//        }
//
//        val numeros = nieReemplazado.dropLast(1).toInt()
//        val letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE"[numeros % 23]
//        val letraIngresada = nif.last().uppercaseChar()
//
//        return letraCalculada == letraIngresada
//    }
//
//    fun guardarFacturaEmitida(nombre: String, nif: String, direccion: String) {
//        val db = FirebaseFirestore.getInstance()
//        val coleccion = "clientes"
//
//        val nombre = _companiaNombre.value ?: ""
//        val nif = _nif.value ?: ""
//        val direccion = _direccion.value ?: ""
//
//        val nuevoDocumento = db.collection(coleccion).document()
//
//        val cliente = Cliente(
//            id = nuevoDocumento.id,
//            nombre = nombre,
//            nif = nif,
//            direccion = direccion
//        )
//
//        nuevoDocumento.set(cliente)
//            .addOnSuccessListener {
//                // Guardar el ID en la variable LiveData y en la variable local
//                _clienteId.value = cliente.id
//                clienteIdLocal = cliente.id  // Almacenar localmente
//
//                // Limpiar los campos
//                _companiaNombre.value = ""
//                _nif.value = ""
//                _direccion.value = ""
//
//                println("Se han guardado los datos correctamente. ID: ${cliente.id}")
//            }
//            .addOnFailureListener { e ->
//                println("Error al agregar documento: $e")
//            }
//    }
//
////    // Función para recuperar el clienteId almacenado localmente
////    fun obtenerClienteIdLocal(): String? {
////        return clienteIdLocal
////    }
//
//    fun actualizarCompaniaNombre(nuevoNombre: String) {
//        _companiaNombre.value = nuevoNombre
//    }
//
//    fun actualizarNif(nuevoNif: String) {
//        _nif.value = nuevoNif
//    }
//
//    fun actualizarDireccion(nuevaDireccion: String) {
//        _direccion.value = nuevaDireccion
//    }
//
//    private val _valor = MutableLiveData<String>()
//    val valor: LiveData<String> = _valor
//
//    private val _iva = MutableLiveData<Int>()
//    val iva: LiveData<Int> = _iva
//
//    private val _total = MutableLiveData<Double>()
//    val total: LiveData<Double> = _total
//
//    fun actualizarValor(nuevoValor: String) {
//        _valor.value = nuevoValor
//        calcularTotal()
//    }
//
//    fun actualizarIVA(nuevoIVA: Int) {
//        _iva.value = nuevoIVA
//        calcularTotal()
//    }
//
//    // ** Function to calculate the total based on value and IVA **
//    private fun calcularTotal() {
//        val valorDouble = _valor.value?.toDoubleOrNull() ?: 0.0
//        val ivaValue = _iva.value ?: 21
//        val totalValue = valorDouble * (1 + ivaValue / 100.0)
//        _total.value = totalValue
//    }
//
//
//
//}
//


class FormularioFEViewModel : ViewModel() {

    private val _companiaNombre = MutableLiveData<String>()
    val companiaNombre: LiveData<String> = _companiaNombre

    private val _nif = MutableLiveData<String>()
    val nif: LiveData<String> = _nif

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    private val _nifError = MutableLiveData<String?>()
    val nifError: LiveData<String?> = _nifError

    private val _clienteId = MutableLiveData<String?>()
    val clienteId: LiveData<String?> = _clienteId

    // ** Variable local para almacenar el clienteId **
    private var clienteIdLocal: String? = null

    fun actualizarNIF(nuevoNIF: String) {
        if (validarNIF(nuevoNIF)) {
            _nif.value = nuevoNIF
            _nifError.value = null
        } else {
            _nifError.value = "NIF inválido"
        }
    }

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

    fun guardarFacturaEmitida(nombre: String, nif: String, direccion: String) {
        val db = FirebaseFirestore.getInstance()
        val coleccion = "clientes"

        val nombre = _companiaNombre.value ?: ""
        val nif = _nif.value ?: ""
        val direccion = _direccion.value ?: ""

        val nuevoDocumento = db.collection(coleccion).document()

        val cliente = Cliente(
            id = nuevoDocumento.id,
            nombre = nombre,
            nif = nif,
            direccion = direccion
        )

        nuevoDocumento.set(cliente)
            .addOnSuccessListener {
                // Guardar el ID en la variable LiveData y en la variable local
                _clienteId.value = cliente.id
                clienteIdLocal = cliente.id  // Almacenar localmente

                // Limpiar los campos
                _companiaNombre.value = ""
                _nif.value = ""
                _direccion.value = ""

                println("Se han guardado los datos correctamente. ID: ${cliente.id}")
            }
            .addOnFailureListener { e ->
                println("Error al agregar documento: $e")
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

    private val _valor = MutableLiveData<String>()
    val valor: LiveData<String> = _valor

    private val _iva = MutableLiveData<Int>()
    val iva: LiveData<Int> = _iva

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> = _total

    fun actualizarValor(nuevoValor: String) {
        _valor.value = nuevoValor
        calcularTotal()
    }

    fun actualizarIVA(nuevoIVA: Int) {
        _iva.value = nuevoIVA
        calcularTotal()
    }

    // ** Function to calculate the total based on value and IVA **
    private fun calcularTotal() {
        val valorDouble = _valor.value?.toDoubleOrNull() ?: 0.0
        val ivaValue = _iva.value ?: 21
        val totalValue = valorDouble * (1 + ivaValue / 100.0)
        _total.value = totalValue
    }


}
/*
fun obtenerTodosLosDatos(): Factura {
        return Factura(
            companiaNombre = _companiaNombre.value?: "",
            nif = _nif.value?: "",
            direccion = _direccion.value?: "",
            valor = _valor.value ?: 0.0,
            iva = _iva.value?: 21,
            total = _total.value ?: 0.0 // En caso de que el total no esté calculado aún
        )
    }
 */