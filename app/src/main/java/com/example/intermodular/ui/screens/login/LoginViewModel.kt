package com.example.intermodular.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseUser


class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> = _password

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    // Variables to store user info
    private val _companiaNombre = MutableLiveData<String>()
    val companiaNombre: LiveData<String> = _companiaNombre

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _nif = MutableLiveData<String>()
    val nif: LiveData<String> = _nif

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _displayName = MutableLiveData<String>()
    val displayName: LiveData<String> = _displayName

    fun actualizarEmail(newEmail: String) {
        _email.value = newEmail
        validateForm()
    }

    fun actualizarPassword(newPassword: String) {
        _password.value = newPassword
        validateForm()
    }

    private fun validateForm() {
        _isFormValid.value = !_email.value.isNullOrBlank() && !_password.value.isNullOrBlank()
    }

    fun iniciarSesion() {
        val userEmail = _email.value
        val userPassword = _password.value

        if (!userEmail.isNullOrBlank() && !userPassword.isNullOrBlank()) {
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginResult.value = true
                        _errorMessage.value = null

                        // Get additional user data (e.g., company name and user ID)
                        val user = auth.currentUser
                        _companiaNombre.value =
                            "User's Company Name" // Replace with actual company name logic
                        _userId.value = user?.uid ?: ""


                    } else {
                        _loginResult.value = false
                        _errorMessage.value = task.exception?.message
                    }
                }
        }
    }


    fun guardarDatosUsuario(context: Context, user: FirebaseUser?) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            // Store FirebaseUser data
            putString("user_id", user?.uid)
            putString("email", user?.email)

            // Store additional data from the Firestore document
            putString("company_name", _companiaNombre.value ?: "")
            putString("address", _address.value ?: "")
            putString("nif", _nif.value ?: "")

            // If the user has any other relevant fields (e.g., phone number, display name)
            putString("phone_number", user?.phoneNumber)
            putString("display_name", user?.displayName)

            Log.d("LoginViewModel", "guardarDatosUsuario: user_id: ${user?.uid}, email: ${user?.email}, company_name: ${_companiaNombre.value}, address: ${_address.value}, nif: ${_nif.value}, phone_number: ${user?.phoneNumber}, display_name: ${user?.displayName}")

            apply()
        }
    }

}