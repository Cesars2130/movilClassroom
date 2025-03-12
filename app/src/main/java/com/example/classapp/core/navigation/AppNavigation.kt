package com.example.classapp.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.classapp.src.login.presentation.LoginViewModel
import com.example.classapp.src.login.presentation.LoginUi
import com.example.classapp.src.register.presentation.RegisterUi
import com.example.classapp.src.register.presentation.RegisterViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel

    ){
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginUi(viewModel = loginViewModel, navController)
        }
        composable("register"  ){
            RegisterUi(viewModel = registerViewModel, navController)
        }
    }
}
