package com.example.intermodular.ui.screens.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _totalFacturas = MutableLiveData<Double>()
    val totalFacturas: LiveData<Double> get() = _totalFacturas

    private val _totalFacturasRe = MutableLiveData<Double>()
    val totalFacturasRe: LiveData<Double> get() = _totalFacturasRe

    private val _totalResta = MutableLiveData<Double>()
    val totalResta: LiveData<Double> get() = _totalResta

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    fun cargarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "") ?: ""
        _email.value = email

        Log.d("HomeViewModel", "Email del usuario cargado: $email")

        if (email.isNotEmpty()) {
            obtenerTotales(email)
        }
    }

    private fun obtenerTotales(emailUsuario: String) {
        var totalFacturasTemp: Double? = null
        var totalFacturasReTemp: Double? = null

        obtenerTotalDeColeccion("facturas", emailUsuario) { total ->
            totalFacturasTemp = total
            _totalFacturas.postValue(total)

            if (totalFacturasTemp != null && totalFacturasReTemp != null) {
                actualizarResta(totalFacturasTemp!!, totalFacturasReTemp!!)
            }
        }

        obtenerTotalDeColeccion("facturasRe", emailUsuario) { total ->
            totalFacturasReTemp = total
            _totalFacturasRe.postValue(total)

            if (totalFacturasTemp != null && totalFacturasReTemp != null) {
                actualizarResta(totalFacturasTemp!!, totalFacturasReTemp!!)
            }
        }
    }

    private fun obtenerTotalDeColeccion(coleccion: String, emailUsuario: String, callback: (Double) -> Unit) {
        firestore.collection(coleccion)
            .whereEqualTo("emailUsuario", emailUsuario)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    Log.d("HomeViewModel", "No hay documentos en $coleccion para $emailUsuario")
                }

                var total = 0.0
                for (document in querySnapshot) {
                    val totalFactura = document.getDouble("total") ?: 0.0
                    total += totalFactura
                }

                Log.d("HomeViewModel", "Total de $coleccion para $emailUsuario: $total")
                callback(total)
            }
            .addOnFailureListener { e ->
                Log.e("HomeViewModel", "Error obteniendo documentos de $coleccion: $e")
            }
    }

    private fun actualizarResta(totalFacturasValue: Double, totalFacturasReValue: Double) {
        val diferencia = totalFacturasValue - totalFacturasReValue
        _totalResta.postValue(diferencia)
        Log.d("HomeViewModel", "Diferencia actualizada: $totalFacturasValue - $totalFacturasReValue = $diferencia")
    }
}