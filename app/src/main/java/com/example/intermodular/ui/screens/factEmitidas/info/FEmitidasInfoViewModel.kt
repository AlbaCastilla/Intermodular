package com.example.intermodular.ui.screens.factEmitidas.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intermodular.data.ConexionBaseDatos
import kotlinx.coroutines.launch

class FEmitidasInfoViewModel : ViewModel() {

    // Variables del ViewModel
    private val _moneyCharge = MutableLiveData<String>("")
    val moneyCharge: LiveData<String> = _moneyCharge

    private val _totalAccount = MutableLiveData<String>("0.00")
    val totalAccount: LiveData<String> = _totalAccount

    private val _taxType = MutableLiveData<Double>(0.21)
    val taxType: LiveData<Double> = _taxType

    private val _guardadoExitoso = MutableLiveData<Boolean>()
    val guardadoExitoso: LiveData<Boolean> = _guardadoExitoso

    // Función para actualizar el valor de moneyCharge
    fun actualizarMoneyCharge(nuevoValor: String) {
        _moneyCharge.value = nuevoValor
        calcularTotal()
    }

    // Función para actualizar el tipo de impuesto
    fun actualizarTaxType(nuevoTipo: String) {
        val taxOptions = mapOf(
            "IVA" to 0.21,  //21%
            "ISR" to 0.10,  //10%
            "IEPS" to 0.04  //4%
        )
        val nuevoImpuesto = taxOptions[nuevoTipo] ?: 0.21
        _taxType.value = nuevoImpuesto
        calcularTotal()
    }

    // Función para calcular el total con el impuesto seleccionado
    private fun calcularTotal() {
        val chargeString = _moneyCharge.value ?: "0.00"
        val charge = chargeString.toDoubleOrNull() ?: 0.0
        val tax = _taxType.value ?: 0.21

        val total = charge * (1 + tax)
        val totalFormatted = String.format("%.2f", total)
        _totalAccount.value = totalFormatted
    }

    // Función para guardar la factura emitida
    fun guardarFacturaEmitida() {
        val moneyCharge = _moneyCharge.value ?: ""
        val totalAccount = _totalAccount.value ?: ""
        val taxType = _taxType.value ?: 0.21

        viewModelScope.launch {
            try {
                ConexionBaseDatos.conexionBaseDatos.collection("facturas")
                    .add(
                        hashMapOf(
                            "moneyCharge" to moneyCharge,
                            "totalAccount" to totalAccount,
                            "taxType" to taxType
                        )
                    ).addOnSuccessListener {
                        _guardadoExitoso.value = true
                        _moneyCharge.value = ""
                        _totalAccount.value = "0.00"
                        _taxType.value = 0.21
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

    fun actualizarMoneyCharge2(nuevoMoneyCharge: String) {
        _moneyCharge.value = moneyCharge.toString()
    }

    fun actualizarTotalAccount(nuevoTotalAccount: String) {
        _totalAccount.value = totalAccount.toString()
    }

    fun actualizarTaxType2(nuevoTaxType: String) {
       // _taxType.value = taxType // me da error y no se pq
    }




}
