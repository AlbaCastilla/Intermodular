package com.example.intermodular.ui.screens.factRecibidas.screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intermodular.models.Factura
import com.google.firebase.firestore.FirebaseFirestore

class FRHomeViewModel: ViewModel() {

    private val _companias = MutableLiveData<List<String>>()
    val companias: LiveData<List<String>> = _companias

    private val _direcciones = MutableLiveData<List<String>>()
    val direcciones: LiveData<List<String>> = _direcciones

    private val _totales = MutableLiveData<List<Double>>()
    val totales: LiveData<List<Double>> = _totales

    private val _facturas = MutableLiveData<List<Factura>>()
    val facturas: LiveData<List<Factura>> = _facturas

    val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    fun cargarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "") ?: ""

        if (email.isEmpty()) {
            Log.d("Firestore", "No se encontró correo en SharedPreferences")
        } else {
            Log.d("Firestore", "Correo cargado: $email")
        }

        _email.value = email
    }

    fun obtenerFacturas(emailUsuario: String) {
        val db = FirebaseFirestore.getInstance()

        Log.d("Firestore", "Consultando facturas para el email: $emailUsuario")

        // Cambié el campo de "email" a "emailUsuario"
        db.collection("facturasRe")
            .whereEqualTo("emailUsuario", emailUsuario)
            .get()
            .addOnSuccessListener { documents ->
                Log.d("Firestore", "Número de documentos encontrados: ${documents.size()}")

                val listaFacturas = mutableListOf<Factura>()
                val listaCompanias = mutableListOf<String>()
                val listaDirecciones = mutableListOf<String>()
                val listaTotales = mutableListOf<Double>()

                for (document in documents) {
                    Log.d("Firestore", "Documento encontrado: ${document.data}")

                    val factura = Factura(
                        companiaNombre = document.getString("companiaNombre") ?: "",
                        direccion = document.getString("direccion") ?: "",
                        total = document.getDouble("total") ?: 0.0
                    )
                    listaFacturas.add(factura)
                    listaCompanias.add(factura.companiaNombre)
                    listaDirecciones.add(factura.direccion)
                    listaTotales.add(factura.total)
                }

                _facturas.value = listaFacturas
                _companias.value = listaCompanias
                _direcciones.value = listaDirecciones
                _totales.value = listaTotales
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error obteniendo facturas", e)
            }
    }

}