package com.example.classapp.src.login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.classapp.core.storage.UserPreferences
import com.example.classapp.src.login.domain.LoginUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf

class LoginViewModel(
    application: Application,
    private val loginUseCase: LoginUseCase
) : AndroidViewModel(application) {

    private val userPreferences = UserPreferences(application.applicationContext)

    var loginSuccess = mutableStateOf<Boolean?>(null)
        private set

    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = loginUseCase.execute(email, password)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        // Guardar los datos del usuario en `DataStore`
                        userPreferences.saveUserData(
                            loginResponse.user.id_user,
                            loginResponse.user.name,
                            loginResponse.user.email,
                            loginResponse.user.id_role,
                            loginResponse.token
                        )

                        println("Logeo Exitoso: ${loginResponse.user}")
                        loginSuccess.value = true
                        onSuccess() // Navegar a otra pantalla
                    } else {
                        println("Respuesta del servidor vacía")
                        loginSuccess.value = false
                        onError("Error al procesar la respuesta del servidor")
                    }
                } else {
                    println("Nombre o Contraseña inválidos")
                    loginSuccess.value = false
                    onError("Nombre o Contraseña inválidos")
                }
            } catch (e: Exception) {
                println("Error en la solicitud: ${e.message}")
                loginSuccess.value = false
                onError("Error en el login: ${e.message}")
            }
        }
    }

    fun resetLoginState() {
        loginSuccess.value = null
    }
}