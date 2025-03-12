package com.example.classapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.classapp.core.navigation.AppNavigation
import com.example.classapp.src.login.data.datasource.RetrofitClientLogin
import com.example.classapp.src.login.presentation.LoginViewModel
import com.example.classapp.src.login.data.repository.LoginRepository
import com.example.classapp.src.login.domain.LoginUseCase
import com.example.classapp.src.login.presentation.LoginViewModelFactory
import com.example.classapp.src.register.data.datasource.RetrofitClient
import com.example.classapp.src.register.data.repository.RegisterRepository
import com.example.classapp.src.register.domain.CreateUserUseCase
import com.example.classapp.src.register.presentation.RegisterViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Crear Repository y UseCase
            val repository = LoginRepository(RetrofitClientLogin.api)
            val loginUseCase = LoginUseCase(repository)
            val registerViewModel = RegisterViewModel(CreateUserUseCase(RegisterRepository(
                RetrofitClient.api)))


            // Crear ViewModel correctamente
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(application, loginUseCase)
            )

            AppNavigation(
                navController = navController,
                loginViewModel = loginViewModel,
                registerViewModel = registerViewModel
            )
        }
    }
}
