package com.example.intermodular.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

object ConexionBaseDatos {

    val conexionBaseDatos: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    val conexionAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

}
