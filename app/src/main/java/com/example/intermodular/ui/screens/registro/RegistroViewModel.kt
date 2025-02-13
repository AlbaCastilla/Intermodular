package com.example.intermodular.ui.screens.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistroViewModel: ViewModel() {

    //VARIABLES DEL VIEWMODEL
    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> = _companyName

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _nif = MutableLiveData<String?>()
    val nif: LiveData<String?> = _nif

    private val _confirmPassword = MutableLiveData<String?>()
    val confirmPassword: LiveData<String?> = _confirmPassword

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> = _password


}