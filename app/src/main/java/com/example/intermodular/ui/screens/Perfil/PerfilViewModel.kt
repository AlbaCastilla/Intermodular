package com.example.intermodular.ui.screens.Perfil


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.intermodular.models.User
/*
class PerfilViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()  // Initialize FirebaseAuth
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()  // Initialize Firestore

    val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> get() = _companyName

    val _address = MutableLiveData<String>()
    val address: LiveData<String> get() = _address

    val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    val _nif = MutableLiveData<String>()
    val nif: LiveData<String> get() = _nif

    fun cargarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Load values from SharedPreferences and update LiveData
        _companyName.value = sharedPreferences.getString("company_name", "") ?: ""
        _address.value = sharedPreferences.getString("address", "") ?: ""  // Fixed sharedPreferences key
        _nif.value = sharedPreferences.getString("nif", "") ?: ""
        _email.value = sharedPreferences.getString("email", "") ?: ""

        Log.d("SharedPreferences", "company_name: ${_companyName.value}")
        Log.d("SharedPreferences", "NIF: ${_nif.value}")
        Log.d("SharedPreferences", "address: ${_address.value}")
        Log.d("SharedPreferences", "Email: ${_email.value}")
    }

    fun guardarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the updated values to SharedPreferences
        editor.putString("company_name", _companyName.value)
        editor.putString("address", _address.value)
        editor.putString("nif", _nif.value)
        editor.putString("email", _email.value)

        // Apply the changes asynchronously
        editor.apply()
    }

    fun actualizarUsuarioEnFirebase(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "") ?: ""

        if (email.isNotEmpty()) {
            val usuarioRef = firestore.collection("usuarios")
                .whereEqualTo("email", email)  // Query Firestore to find the user with the matching email

            usuarioRef.get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        // User document found, update it
                        val usuarioId = querySnapshot.documents[0].id // Get the document ID
                        val usuarioData: Map<String, Any> = hashMapOf(
                            "companiaNombre" to (_companyName.value ?: ""),
                            "nif" to (_nif.value ?: ""),
                            "direccion" to (_address.value ?: ""),
                            "email" to (_email.value ?: "")
                        )

                        // Now update the document
                        val usuarioDocumentRef = firestore.collection("usuarios").document(usuarioId)
                        usuarioDocumentRef.update(usuarioData)
                            .addOnSuccessListener {
                                Log.d("PerfilViewModel", "User data updated in Firestore successfully.")
                            }
                            .addOnFailureListener { e ->
                                Log.e("PerfilViewModel", "Error updating user data in Firestore: $e")
                            }
                    } else {
                        Log.e("PerfilViewModel", "No user found with the email: $email")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("PerfilViewModel", "Error fetching user data from Firestore: $e")
                }
        } else {
            Log.e("PerfilViewModel", "Email not found in SharedPreferences.")
        }
    }


}*/
class PerfilViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> get() = _companyName

    val _address = MutableLiveData<String>()
    val address: LiveData<String> get() = _address

    val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    val _nif = MutableLiveData<String>()
    val nif: LiveData<String> get() = _nif

    // Nueva LiveData para el estado de éxito
    val _userUpdated = MutableLiveData<Boolean>()
    val userUpdated: LiveData<Boolean> get() = _userUpdated

    fun cargarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        _companyName.value = sharedPreferences.getString("company_name", "") ?: ""
        _address.value = sharedPreferences.getString("address", "") ?: ""
        _nif.value = sharedPreferences.getString("nif", "") ?: ""
        _email.value = sharedPreferences.getString("email", "") ?: ""
    }

    fun guardarDatosUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("company_name", _companyName.value)
        editor.putString("address", _address.value)
        editor.putString("nif", _nif.value)
        editor.putString("email", _email.value)
        editor.apply()
    }

    fun actualizarUsuarioEnFirebase(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "") ?: ""

        if (email.isNotEmpty()) {
            val usuarioRef = firestore.collection("usuarios")
                .whereEqualTo("email", email)

            usuarioRef.get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val usuarioId = querySnapshot.documents[0].id
                        val usuarioData: Map<String, Any> = hashMapOf(
                            "companiaNombre" to (_companyName.value ?: ""),
                            "nif" to (_nif.value ?: ""),
                            "direccion" to (_address.value ?: ""),
                            "email" to (_email.value ?: "")
                        )

                        val usuarioDocumentRef = firestore.collection("usuarios").document(usuarioId)
                        usuarioDocumentRef.update(usuarioData)
                            .addOnSuccessListener {
                                _userUpdated.value = true  // Set the update status to true
                            }
                            .addOnFailureListener { e ->
                                Log.e("PerfilViewModel", "Error updating user data in Firestore: $e")
                                _userUpdated.value = false  // Set the update status to false if failed
                            }
                    } else {
                        _userUpdated.value = false  // If user not found
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("PerfilViewModel", "Error fetching user data from Firestore: $e")
                    _userUpdated.value = false
                }
        } else {
            _userUpdated.value = false
        }
    }

    fun logout(context: Context) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        // Cerrar sesión del usuario
        auth.signOut()

        // Eliminar los datos del usuario almacenados en SharedPreferences
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()  // Limpiar los datos guardados
        editor.apply()
    }

}


