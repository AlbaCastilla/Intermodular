package com.example.intermodular.ui.screens.factRecibidas.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FRHomeViewModel: ViewModel() {

    private val _companias = MutableLiveData<List<String>>()
    val companias: LiveData<List<String>> = _companias

    private val _direcciones = MutableLiveData<List<String>>()
    val direcciones: LiveData<List<String>> = _direcciones

    private val _totales = MutableLiveData<List<Double>>()
    val totales: LiveData<List<Double>> = _totales

    fun obtenerDatos() {
        val db = FirebaseFirestore.getInstance()
        val coleccion = db.collection("facturasRe")

        coleccion.get()
            .addOnSuccessListener { resultado ->
                val listaCompanias = mutableListOf<String>()
                val listaDirecciones = mutableListOf<String>()
                val listaTotales = mutableListOf<Double>()

                for (documento in resultado) {
                    listaCompanias.add(documento.getString("companiaNombre") ?: "")
                    listaDirecciones.add(documento.getString("direccion") ?: "")
                    listaTotales.add(documento.getDouble("total") ?: 0.0)
                }

                _companias.value = listaCompanias
                _direcciones.value = listaDirecciones
                _totales.value = listaTotales
            }
            .addOnFailureListener { e ->
                println("Error al obtener los datos: $e")
            }
    }

}