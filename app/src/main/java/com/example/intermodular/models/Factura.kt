package com.example.intermodular.models

data class Factura(
//    val id: String,
//    val cliente: String,
//    val fecha: String,
//    val proyecto: String,
//    val totalSinIVA: Double,
//    val iva: Double,
//    val totalConIVA: Double
    val companiaNombre: String= "",
    val nif: String= "",
    val direccion: String= "",
    val valor: Comparable<*> =0.0,
    val iva: Int=21,
    val total: Double=0.0
)