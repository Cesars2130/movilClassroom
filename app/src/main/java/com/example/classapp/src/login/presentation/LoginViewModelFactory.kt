package com.example.classapp.src.login.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.classapp.src.login.domain.LoginUseCase

class LoginViewModelFactory(
    private val application: Application,
    private val loginUseCase: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(application, loginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
