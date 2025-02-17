package com.example.intermodular.ui.screens.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.example.intermodular.models.User

//class RegistroViewModel : ViewModel() {
//
//    private val db = FirebaseFirestore.getInstance()
//    private val usuariosCollection = db.collection("usuarios")
//
//    private val _companyName = MutableLiveData<String>()
//    val companyName: LiveData<String> = _companyName
//
//    private val _address = MutableLiveData<String>()
//    val address: LiveData<String> = _address
//
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _nif = MutableLiveData<String?>()
//    val nif: LiveData<String?> = _nif
//
//    private val _password = MutableLiveData<String?>()
//    val password: LiveData<String?> = _password
//
//    private val _confirmPassword = MutableLiveData<String?>()
//    val confirmPassword: LiveData<String?> = _confirmPassword
//
//    private val _isFormValid = MutableLiveData<Boolean>()
//    val isFormValid: LiveData<Boolean> = _isFormValid
//
//    fun actualizarCompanyName(newCompanyName: String) {
//        _companyName.value = newCompanyName
//        validateForm()
//    }
//
//    fun actualizarAddress(newAddress: String) {
//        _address.value = newAddress
//        validateForm()
//    }
//
//    fun actualizarEmail(newEmail: String) {
//        _email.value = newEmail
//        validateForm()
//    }
//
//    fun actualizarNif(newNif: String) {
//        _nif.value = newNif
//        validateForm()
//    }
//
//    fun actualizarPassword(newPassword: String) {
//        _password.value = newPassword
//        validateForm()
//    }
//
//    fun actualizarConfirmPassword(newConfirmPassword: String) {
//        _confirmPassword.value = newConfirmPassword
//        validateForm()
//    }
//
//    private fun validateForm() {
//        _isFormValid.value = !_companyName.value.isNullOrBlank() &&
//                !_address.value.isNullOrBlank() &&
//                !_email.value.isNullOrBlank() &&
//                !_nif.value.isNullOrBlank() &&
//                !_password.value.isNullOrBlank() &&
//                _password.value == _confirmPassword.value
//    }
//
//    fun registrarUsuario() {
//        if (_isFormValid.value == true) {
//            val usuario = hashMapOf(
//                "companiaNombre" to (_companyName.value ?: ""),
//                "nif" to (_nif.value ?: ""),
//                "direccion" to (_address.value ?: ""),
//                "email" to (_email.value ?: "")
//            )
//
//            usuariosCollection.add(usuario)
//                .addOnSuccessListener {
//                    println("Usuario registrado correctamente en Firestore")
//                    limpiarCampos()
//                }
//                .addOnFailureListener { e ->
//                    println("Error al registrar usuario: $e")
//                }
//        }
//    }
//
//    private fun limpiarCampos() {
//        _companyName.value = ""
//        _address.value = ""
//        _email.value = ""
//        _nif.value = ""
//        _password.value = ""
//        _confirmPassword.value = ""
//    }
//}



import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

//class RegistroViewModel : ViewModel() {
//
//    private val db = FirebaseFirestore.getInstance()
//    private val usuariosCollection = db.collection("usuarios")
//    private val auth = FirebaseAuth.getInstance()  // Para usar Firebase Authentication
//
//    private val _companyName = MutableLiveData<String>()
//    val companyName: LiveData<String> = _companyName
//
//    private val _address = MutableLiveData<String>()
//    val address: LiveData<String> = _address
//
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _nif = MutableLiveData<String?>()
//    val nif: LiveData<String?> = _nif
//
//    private val _password = MutableLiveData<String?>()
//    val password: LiveData<String?> = _password
//
//    private val _confirmPassword = MutableLiveData<String?>()
//    val confirmPassword: LiveData<String?> = _confirmPassword
//
//    private val _isFormValid = MutableLiveData<Boolean>()
//    val isFormValid: LiveData<Boolean> = _isFormValid
//
//    private val _errorMessage = MutableLiveData<String?>()
//    val errorMessage: LiveData<String?> = _errorMessage
//
//    fun actualizarCompanyName(newCompanyName: String) {
//        _companyName.value = newCompanyName
//        validateForm()
//    }
//
//    fun actualizarAddress(newAddress: String) {
//        _address.value = newAddress
//        validateForm()
//    }
//
//    fun actualizarEmail(newEmail: String) {
//        _email.value = newEmail
//        validateForm()
//    }
//
//    fun actualizarNif(newNif: String) {
//        _nif.value = newNif
//        validateForm()
//    }
//
//    fun actualizarPassword(newPassword: String) {
//        _password.value = newPassword
//        validateForm()
//    }
//
//    fun actualizarConfirmPassword(newConfirmPassword: String) {
//        _confirmPassword.value = newConfirmPassword
//        validateForm()
//    }
//
//    private fun validateForm() {
//        val emailValid = isValidEmail(_email.value ?: "")
//        val passwordValid = _password.value?.length ?: 0 >= 6 // Aseguramos que la contraseña tenga al menos 6 caracteres
//        val passwordsMatch = _password.value == _confirmPassword.value
//        val nifValid = isValidNif(_nif.value ?: "")
//
//        // Validar si el formulario está completo y las validaciones son correctas
//        _isFormValid.value = !(_companyName.value.isNullOrBlank() ||
//                _address.value.isNullOrBlank() ||
//                !_email.value.isNullOrBlank() && !emailValid ||
//                !_nif.value.isNullOrBlank() && !nifValid ||
//                !_password.value.isNullOrBlank() && !passwordValid ||
//                !passwordsMatch
//                )
//
//        // Establecer los mensajes de error específicos
//        if (_companyName.value.isNullOrBlank()) {
//            _errorMessage.value = "El nombre de la empresa es obligatorio."
//        } else if (_address.value.isNullOrBlank()) {
//            _errorMessage.value = "La dirección es obligatoria."
//        } else if (!_email.value.isNullOrBlank() && !emailValid) {
//            _errorMessage.value = "Por favor, ingrese un correo electrónico válido."
//        } else if (_nif.value.isNullOrBlank() || !nifValid) {
//            _errorMessage.value = "El NIF ingresado no es válido."
//        } else if (_password.value?.length ?: 0 < 6) {
//            _errorMessage.value = "La contraseña debe tener al menos 6 caracteres."
//        } else if (_password.value != _confirmPassword.value) {
//            _errorMessage.value = "Las contraseñas no coinciden."
//        } else {
//            _errorMessage.value = null // Si todo es válido, no hay mensaje de error
//        }
//    }
//
//    private fun isValidEmail(email: String): Boolean {
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }
//
//    private fun isValidNif(nif: String): Boolean {
//        // Suponiendo que el formato del NIF es válido según las reglas locales, puedes agregar una validación más específica
//        return nif.length == 9 && nif[8].isLetter() // Ejemplo para España (9 caracteres, la última letra es la de control)
//    }
//
//    fun registrarUsuario() {
//        if (_isFormValid.value == true) {
//            // Primero registramos al usuario en Firebase Authentication
//            auth.createUserWithEmailAndPassword(_email.value ?: "", _password.value ?: "")
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        // El registro fue exitoso en Authentication
//                        val usuario = hashMapOf(
//                            "companiaNombre" to (_companyName.value ?: ""),
//                            "nif" to (_nif.value ?: ""),
//                            "direccion" to (_address.value ?: ""),
//                            "email" to (_email.value ?: "")
//                        )
//
//                        // Ahora agregamos el usuario a Firestore
//                        usuariosCollection.add(usuario)
//                            .addOnSuccessListener {
//                                println("Usuario registrado correctamente en Firestore")
//                                limpiarCampos()
//                            }
//                            .addOnFailureListener { e ->
//                                println("Error al registrar usuario en Firestore: $e")
//                            }
//                    } else {
//                        // Si hubo un error al registrar en Firebase Authentication
//                        val error = task.exception
//                        when (error) {
//                            is FirebaseAuthInvalidCredentialsException -> {
//                                println("Credenciales inválidas")
//                            }
//                            is FirebaseAuthUserCollisionException -> {
//                                println("El usuario ya existe")
//                            }
//                            else -> {
//                                println("Error al registrar usuario en Firebase Authentication: $error")
//                            }
//                        }
//                    }
//                }
//        }
//    }
//
//    private fun limpiarCampos() {
//        _companyName.value = ""
//        _address.value = ""
//        _email.value = ""
//        _nif.value = ""
//        _password.value = ""
//        _confirmPassword.value = ""
//    }
//}
class RegistroViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val usuariosCollection = db.collection("usuarios")
    private val auth = FirebaseAuth.getInstance()  // Para usar Firebase Authentication

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

    // Añadir LiveData para cada error de campo
    private val _companyNameError = MutableLiveData<String?>()
    val companyNameError: LiveData<String?> = _companyNameError

    private val _addressError = MutableLiveData<String?>()
    val addressError: LiveData<String?> = _addressError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _nifError = MutableLiveData<String?>()
    val nifError: LiveData<String?> = _nifError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _confirmPasswordError = MutableLiveData<String?>()
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

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
        val emailValid = isValidEmail(_email.value ?: "")
        val passwordValid = _password.value?.length ?: 0 >= 6
        val passwordsMatch = _password.value == _confirmPassword.value
        val nifValid = isValidNif(_nif.value ?: "")

        // Validar campos individualmente
        _companyNameError.value = if (_companyName.value.isNullOrBlank()) "El nombre de la empresa es obligatorio." else null
        _addressError.value = if (_address.value.isNullOrBlank()) "La dirección es obligatoria." else null
        _emailError.value = if (!_email.value.isNullOrBlank() && !emailValid) "Por favor, ingrese un correo electrónico válido." else null
        _nifError.value = if (_nif.value.isNullOrBlank() || !nifValid) "El NIF ingresado no es válido." else null
        _passwordError.value = if (_password.value?.length ?: 0 < 6) "La contraseña debe tener al menos 6 caracteres." else null
        _confirmPasswordError.value = if (_password.value != _confirmPassword.value) "Las contraseñas no coinciden." else null

        // Validar si el formulario es válido
        _isFormValid.value = _companyNameError.value == null &&
                _addressError.value == null &&
                _emailError.value == null &&
                _nifError.value == null &&
                _passwordError.value == null &&
                _confirmPasswordError.value == null
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidNif(nif: String): Boolean {
        return nif.length == 9 && nif[8].isLetter()
    }

    fun registrarUsuario() {
        if (_isFormValid.value == true) {
            auth.createUserWithEmailAndPassword(_email.value ?: "", _password.value ?: "")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val usuario = hashMapOf(
                            "companiaNombre" to (_companyName.value ?: ""),
                            "nif" to (_nif.value ?: ""),
                            "direccion" to (_address.value ?: ""),
                            "email" to (_email.value ?: "")
                        )
                        usuariosCollection.add(usuario)
                            .addOnSuccessListener {
                                limpiarCampos()
                            }
                            .addOnFailureListener { e -> println("Error: $e") }
                    } else {
                        val error = task.exception
                        println("Error: $error")
                    }
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

//class RegistroViewModel : ViewModel() {
//
//    private val db = FirebaseFirestore.getInstance()
//    private val usuariosCollection = db.collection("usuarios")
//    private val auth = FirebaseAuth.getInstance()  // Para usar Firebase Authentication
//
//    private val _companyName = MutableLiveData<String>()
//    val companyName: LiveData<String> = _companyName
//
//    private val _address = MutableLiveData<String>()
//    val address: LiveData<String> = _address
//
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _nif = MutableLiveData<String?>()
//    val nif: LiveData<String?> = _nif
//
//    private val _password = MutableLiveData<String?>()
//    val password: LiveData<String?> = _password
//
//    private val _confirmPassword = MutableLiveData<String?>()
//    val confirmPassword: LiveData<String?> = _confirmPassword
//
//    private val _isFormValid = MutableLiveData<Boolean>()
//    val isFormValid: LiveData<Boolean> = _isFormValid
//
//    fun actualizarCompanyName(newCompanyName: String) {
//        _companyName.value = newCompanyName
//        validateForm()
//    }
//
//    fun actualizarAddress(newAddress: String) {
//        _address.value = newAddress
//        validateForm()
//    }
//
//    fun actualizarEmail(newEmail: String) {
//        _email.value = newEmail
//        validateForm()
//    }
//
//    fun actualizarNif(newNif: String) {
//        _nif.value = newNif
//        validateForm()
//    }
//
//    fun actualizarPassword(newPassword: String) {
//        _password.value = newPassword
//        validateForm()
//    }
//
//    fun actualizarConfirmPassword(newConfirmPassword: String) {
//        _confirmPassword.value = newConfirmPassword
//        validateForm()
//    }
//
//    private fun validateForm() {
//        _isFormValid.value = !_companyName.value.isNullOrBlank() &&
//                !_address.value.isNullOrBlank() &&
//                !_email.value.isNullOrBlank() &&
//                !_nif.value.isNullOrBlank() &&
//                !_password.value.isNullOrBlank() &&
//                _password.value == _confirmPassword.value
//    }
//
//    fun registrarUsuario() {
//        if (_isFormValid.value == true) {
//            // Primero registramos al usuario en Firebase Authentication
//            auth.createUserWithEmailAndPassword(_email.value ?: "", _password.value ?: "")
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        // El registro fue exitoso en Authentication
//                        val usuario = hashMapOf(
//                            "companiaNombre" to (_companyName.value ?: ""),
//                            "nif" to (_nif.value ?: ""),
//                            "direccion" to (_address.value ?: ""),
//                            "email" to (_email.value ?: "")
//                        )
//
//                        // Ahora agregamos el usuario a Firestore
//                        usuariosCollection.add(usuario)
//                            .addOnSuccessListener {
//                                println("Usuario registrado correctamente en Firestore")
//                                limpiarCampos()
//                            }
//                            .addOnFailureListener { e ->
//                                println("Error al registrar usuario en Firestore: $e")
//                            }
//                    } else {
//                        // Si hubo un error al registrar en Firebase Authentication
//                        val error = task.exception
//                        when (error) {
//                            is FirebaseAuthInvalidCredentialsException -> {
//                                println("Credenciales inválidas")
//                            }
//                            is FirebaseAuthUserCollisionException -> {
//                                println("El usuario ya existe")
//                            }
//                            else -> {
//                                println("Error al registrar usuario en Firebase Authentication: $error")
//                            }
//                        }
//                    }
//                }
//        }
//    }
//
//    private fun limpiarCampos() {
//        _companyName.value = ""
//        _address.value = ""
//        _email.value = ""
//        _nif.value = ""
//        _password.value = ""
//        _confirmPassword.value = ""
//    }
//}
