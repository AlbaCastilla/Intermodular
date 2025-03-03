package com.example.intermodular.ui.screens.factEmitidas.form

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intermodular.models.Cliente
import com.example.intermodular.models.Factura
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


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

    private val _companiaNombreUsuario = MutableLiveData<String>()
    val companiaNombreUsuario: LiveData<String> = _companiaNombreUsuario

    private val _nifUsuario = MutableLiveData<String>()
    val nifUsuario: LiveData<String> = _nifUsuario

    private val _direccionUsuario = MutableLiveData<String>()
    val direccionUsuario: LiveData<String> = _direccionUsuario

    private val _emailUsuario = MutableLiveData<String>()
    val emailUsuario: LiveData<String> = _emailUsuario

    private val _phoneNumberUsuario = MutableLiveData<String>()
    val phoneNumberUsuario: LiveData<String> = _phoneNumberUsuario

    private val _displayNameUsuario = MutableLiveData<String>()
    val displayNameUsuario: LiveData<String> = _displayNameUsuario

    private var usuarioId: String? = null

    fun cargarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Recuperar los datos guardados en SharedPreferences
        _companiaNombreUsuario.value = sharedPreferences.getString("company_name", "")
        _nifUsuario.value = sharedPreferences.getString("nif", "")
        _direccionUsuario.value = sharedPreferences.getString("address", "")
        _emailUsuario.value = sharedPreferences.getString("email", "")
        _phoneNumberUsuario.value = sharedPreferences.getString("phone_number", "")
        usuarioId = sharedPreferences.getString("user_id", "")
        _displayNameUsuario.value = sharedPreferences.getString("display_name", "")

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

    fun guardarFacturaEnFirestore() {
        val db = FirebaseFirestore.getInstance()
        val coleccion = db.collection("facturas")

        // Obtener el último número de factura
        coleccion.orderBy("numeroFactura", Query.Direction.DESCENDING).limit(1).get()
            .addOnSuccessListener { documents ->
                var nuevoNumero = 1
                if (!documents.isEmpty) {
                    val ultimoNumero = documents.documents[0].getLong("numeroFactura") ?: 0
                    nuevoNumero = ultimoNumero.toInt() + 1
                }
                val numeroFacturaStr = nuevoNumero.toString().padStart(7, '0')

                val factura = hashMapOf(
                    "numeroFactura" to nuevoNumero,
                    "companiaNombre" to companiaNombre.value,
                    "nif" to nif.value,
                    "direccion" to direccion.value,
                    "valor" to valor.value,
                    "iva" to iva.value,
                    "total" to total.value,
                    "companiaNombreUsuario" to companiaNombreUsuario.value,
                    "nifUsuario" to nifUsuario.value,
                    "direccionUsuario" to direccionUsuario.value,
                    "emailUsuario" to emailUsuario.value,
                    "phoneNumberUsuario" to phoneNumberUsuario.value,
                    "displayNameUsuario" to displayNameUsuario.value,
                    "timestamp" to FieldValue.serverTimestamp()
                )

                coleccion.add(factura)
                    .addOnSuccessListener { documentReference ->
                        Log.d("Firestore", "Factura guardada con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error al guardar la factura", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error al obtener el último número de factura", e)
            }
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