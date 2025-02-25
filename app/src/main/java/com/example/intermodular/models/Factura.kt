package com.example.intermodular.models

data class Factura(
    val id: String,
    val cliente: String,
    val fecha: String,
    val proyecto: String,
    val totalSinIVA: Double,
    val iva: Double,
    val totalConIVA: Double
)