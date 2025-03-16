package com.example.classapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.classapp.core.navigation.AppNavigation
import com.example.classapp.core.storage.UserPreferences
import com.example.classapp.src.createClass.data.CreateClassRepository
import com.example.classapp.src.createClass.domain.CreateClassUseCase
import com.example.classapp.src.createClass.presentation.CreateClassViewModel
import com.example.classapp.src.login.data.datasource.RetrofitClientLogin
import com.example.classapp.src.login.presentation.LoginViewModel
import com.example.classapp.src.login.data.repository.LoginRepository
import com.example.classapp.src.login.domain.LoginUseCase
import com.example.classapp.src.login.presentation.LoginViewModelFactory
import com.example.classapp.src.register.data.datasource.RetrofitClient
import com.example.classapp.src.register.data.repository.RegisterRepository
import com.example.classapp.src.register.domain.CreateUserUseCase
import com.example.classapp.src.register.presentation.RegisterViewModel
import com.example.classapp.src.viewActivities.data.repository.CreateActivityRepository
import com.example.classapp.src.viewActivities.data.repository.ViewActivitiesRepository
import com.example.classapp.src.viewActivities.domain.CreateActivityUseCase
import com.example.classapp.src.viewActivities.domain.ViewActivitiesUseCase
import com.example.classapp.src.viewActivities.presentation.CreateActivityViewModel
import com.example.classapp.src.viewActivities.presentation.ViewActivitiesViewModel
import com.example.classapp.src.viewTasks.data.datasource.RetrofitClientViewTask
import com.example.classapp.src.viewTasks.data.repository.ViewTaskRepository
import com.example.classapp.src.viewTasks.domain.ViewTaskUseCase
import com.example.classapp.src.viewTasks.presentation.ViewTaskViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Crear instancia de UserPreferences
            val userPreferences = UserPreferences(applicationContext) // 🔹 Crear instancia aquí

            // Crear Repository y UseCase
            val repository = LoginRepository(RetrofitClientLogin.api)
            val loginUseCase = LoginUseCase(repository)
            val registerViewModel = RegisterViewModel(CreateUserUseCase(RegisterRepository(
                RetrofitClient.api)))

            val viewTasksViewModel = ViewTaskViewModel(
                useCase = ViewTaskUseCase(ViewTaskRepository()),
                userPreferences = userPreferences // 🔹 Pasar `userPreferences`
            )
            val createClassViewModel = CreateClassViewModel(CreateClassUseCase(CreateClassRepository()))


            val viewActivitiesViewModel = ViewActivitiesViewModel(ViewActivitiesUseCase(
                ViewActivitiesRepository()
            ))

            // Crear ViewModel correctamente
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(application, loginUseCase)
            )
            val createActivityViewModel = CreateActivityViewModel(
                CreateActivityUseCase(CreateActivityRepository())
            )



            // 🔹 Ahora pasamos `userPreferences` a `AppNavigation`
            AppNavigation(
                navController = navController,
                loginViewModel = loginViewModel,
                registerViewModel = registerViewModel,
                viewTasksViewModel = viewTasksViewModel,
                viewActivitiesViewModel = viewActivitiesViewModel,
                userPreferences = userPreferences,
                createClassViewModel = createClassViewModel,
                createActivityViewModel = createActivityViewModel,
            )
        }
    }
}
