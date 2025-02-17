package com.example.intermodular.ui.screens.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.example.intermodular.models.User

class RegistroViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val usuariosCollection = db.collection("usuarios")

    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> = _companyName

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _nif = MutableLiveData<String?>()
    val nif: LiveData<String?> = _nif

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> = _password

    private val _confirmPassword = MutableLiveData<String?>()
    val confirmPassword: LiveData<String?> = _confirmPassword

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    fun actualizarCompanyName(newCompanyName: String) {
        _companyName.value = newCompanyName
        validateForm()
    }

    fun actualizarAddress(newAddress: String) {
        _address.value = newAddress
        validateForm()
    }

    fun actualizarEmail(newEmail: String) {
        _email.value = newEmail
        validateForm()
    }

    fun actualizarNif(newNif: String) {
        _nif.value = newNif
        validateForm()
    }

    fun actualizarPassword(newPassword: String) {
        _password.value = newPassword
        validateForm()
    }

    fun actualizarConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
        validateForm()
    }

    private fun validateForm() {
        _isFormValid.value = !_companyName.value.isNullOrBlank() &&
                !_address.value.isNullOrBlank() &&
                !_email.value.isNullOrBlank() &&
                !_nif.value.isNullOrBlank() &&
                !_password.value.isNullOrBlank() &&
                _password.value == _confirmPassword.value
    }

    fun registrarUsuario() {
        if (_isFormValid.value == true) {
            val usuario = hashMapOf(
                "companiaNombre" to (_companyName.value ?: ""),
                "nif" to (_nif.value ?: ""),
                "direccion" to (_address.value ?: ""),
                "email" to (_email.value ?: "")
            )

            usuariosCollection.add(usuario)
                .addOnSuccessListener {
                    println("Usuario registrado correctamente en Firestore")
                    limpiarCampos()
                }
                .addOnFailureListener { e ->
                    println("Error al registrar usuario: $e")
                }
        }
    }

    private fun limpiarCampos() {
        _companyName.value = ""
        _address.value = ""
        _email.value = ""
        _nif.value = ""
        _password.value = ""
        _confirmPassword.value = ""
    }
}
