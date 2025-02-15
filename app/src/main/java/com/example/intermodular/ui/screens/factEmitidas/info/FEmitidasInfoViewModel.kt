package com.example.intermodular.ui.screens.factEmitidas.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FEmitidasInfoViewModel: ViewModel() {

    private val _moneyCharge = MutableLiveData<String>("") //le vamos aa poner comillas para q no me de error con el input
    val moneyCharge: LiveData<String> = _moneyCharge

    private val _totalAccount = MutableLiveData<String>("0.00") //indicamos qe siempre va a comenzar en 0.00
    val totalAccount: LiveData<String> = _totalAccount

    private val _taxType = MutableLiveData<Double>(0.21) //q empiece por defecto con la opcion del 21%
    val taxType: LiveData<Double> = _taxType

    //para las opciones del select para los impuestos
    private val taxOptions = mapOf(
        "IVA" to 0.21,  //21%
        "ISR" to 0.10,  //10%
        "IEPS" to 0.04  //4%
    )


    fun updateMoneyCharge(value: String) {
        _moneyCharge.value = value
        calculateTotal()
    }

    fun updateTaxType(taxName: String) {
        val newTax = taxOptions[taxName] ?: 0.21
        _taxType.value = newTax
        calculateTotal()
    }

    private fun calculateTotal() {
        //obtenemos la cantidad del moneyCharge --> como Double
        val chargeString = _moneyCharge.value ?: "0.00"
        val charge = chargeString.toDoubleOrNull() ?: 0.0

        //pillamos el valor del impuesto q hemos seleccionado
        val tax = _taxType.value ?: 0.21

        // Calcular el total con impuestos
        val total = charge * (1 + tax)

        // Convertir el resultado a String con 2 decimales y actualizar LiveData
        val totalFormatted = String.format("%.2f", total)
        _totalAccount.value = totalFormatted
    }
}