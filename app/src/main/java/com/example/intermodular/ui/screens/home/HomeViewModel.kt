package com.example.intermodular.ui.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _totalFacturas = MutableLiveData<Double>()
    val totalFacturas: LiveData<Double> get() = _totalFacturas

    private val _totalFacturasRe = MutableLiveData<Double>()
    val totalFacturasRe: LiveData<Double> get() = _totalFacturasRe

    private val _totalResta = MutableLiveData<Double>()
    val totalResta: LiveData<Double> get() = _totalResta

    init {
        obtenerTotales()
    }

    private fun obtenerTotales() {
        // Obtener los totales de las facturas y facturasRe
        obtenerTotalDeColeccion("facturas") { total ->
            _totalFacturas.value = total
        }
        obtenerTotalDeColeccion("facturasRe") { total ->
            _totalFacturasRe.value = total
        }
    }

    private fun obtenerTotalDeColeccion(coleccion: String, callback: (Double) -> Unit) {
        firestore.collection(coleccion).get()
            .addOnSuccessListener { querySnapshot ->
                var total = 0.0
                for (document in querySnapshot) {
                    val totalFactura = document.getDouble("total") ?: 0.0
                    total += totalFactura
                }
                callback(total)
                actualizarResta()
            }
            .addOnFailureListener { e ->
                Log.e("HomeViewModel", "Error getting documents from $coleccion: $e")
            }
    }

    private fun actualizarResta() {
        val totalFacturasValue = _totalFacturas.value ?: 0.0
        val totalFacturasReValue = _totalFacturasRe.value ?: 0.0
        _totalResta.value = totalFacturasValue - totalFacturasReValue
    }
}
