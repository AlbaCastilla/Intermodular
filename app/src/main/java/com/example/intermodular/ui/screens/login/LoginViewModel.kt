package com.example.intermodular.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

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

    // Variables to store additional user info (company name and ID)
    private val _companiaNombre = MutableLiveData<String>()
    val companiaNombre: LiveData<String> = _companiaNombre

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

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
                        _companiaNombre.value = "User's Company Name" // Replace with actual company name logic
                        _userId.value = user?.uid ?: ""
                    } else {
                        _loginResult.value = false
                        _errorMessage.value = task.exception?.message
                    }
                }
        }
    }
}
