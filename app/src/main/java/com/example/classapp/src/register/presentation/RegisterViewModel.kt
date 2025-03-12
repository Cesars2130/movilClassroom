package com.example.classapp.src.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.classapp.src.register.domain.CreateUserUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _surname = MutableLiveData("")
    val surname: LiveData<String> = _surname

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName

    private val _idRole = MutableLiveData(2) // Por defecto, el usuario es "Alumno" (id_role = 2)
    val idRole: LiveData<Int> = _idRole

    private val _registrationResult = MutableLiveData<RegistrationResult?>()
    val registrationResult: LiveData<RegistrationResult?> = _registrationResult

    fun onNameChanged(newName: String) { _name.value = newName }
    fun onEmailChanged(newEmail: String) { _email.value = newEmail }
    fun onPasswordChanged(newPassword: String) { _password.value = newPassword }
    fun onSurnameChanged(newSurname: String) { _surname.value = newSurname }
    fun onLastNameChanged(newLastName: String) { _lastName.value = newLastName }
    fun onRoleChanged(newRole: Int) { _idRole.value = newRole }
    fun resetRegistrationResult() { _registrationResult.value = null }

    fun resetRegistrationForm() {
        _name.value = ""
        _email.value = ""
        _password.value = ""
        _surname.value = ""
        _lastName.value = ""
        _idRole.value = 2
        _registrationResult.value = null
    }

    fun registerUser() {
        viewModelScope.launch {
            val currentName = _name.value ?: ""
            val currentEmail = _email.value ?: ""
            val currentPassword = _password.value ?: ""
            val currentSurname = _surname.value ?: ""
            val currentLastName = _lastName.value ?: ""
            val currentIdRole = _idRole.value ?: 2

            if (currentName.isEmpty() || currentEmail.isEmpty() || currentPassword.isEmpty() || currentSurname.isEmpty() || currentLastName.isEmpty()) {
                _registrationResult.value = RegistrationResult.Error("Todos los campos son obligatorios")
                return@launch
            }

            try {
                val response = createUserUseCase.execute(
                    name = currentName,
                    email = currentEmail,
                    password = currentPassword,
                    surname = currentSurname,
                    lastName = currentLastName,
                    idRole = currentIdRole
                )

                if (response.isSuccessful) {
                    _registrationResult.value = RegistrationResult.Success
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    _registrationResult.value = RegistrationResult.Error(errorBody)
                }
            } catch (e: Exception) {
                _registrationResult.value = RegistrationResult.Error("Error en el registro: ${e.message}")
            }
        }
    }
}

sealed class RegistrationResult {
    object Success : RegistrationResult()
    data class Error(val message: String) : RegistrationResult()
}
