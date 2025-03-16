package com.example.classapp.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.classapp.core.storage.UserPreferences
import com.example.classapp.src.createClass.presentation.CreateClassScreen
import com.example.classapp.src.createClass.presentation.CreateClassViewModel
import com.example.classapp.src.login.presentation.LoginViewModel
import com.example.classapp.src.login.presentation.LoginUi
import com.example.classapp.src.register.presentation.RegisterUi
import com.example.classapp.src.register.presentation.RegisterViewModel
import com.example.classapp.src.viewActivities.presentation.CreateActivityScreen
import com.example.classapp.src.viewActivities.presentation.CreateActivityViewModel
import com.example.classapp.src.viewActivities.presentation.ViewActivitiesScreen
import com.example.classapp.src.viewActivities.presentation.ViewActivitiesViewModel
import com.example.classapp.src.viewTasks.presentation.ViewTaskUi
import com.example.classapp.src.viewTasks.presentation.ViewTaskViewModel


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    viewTasksViewModel: ViewTaskViewModel,
    viewActivitiesViewModel: ViewActivitiesViewModel,
    userPreferences: UserPreferences, // 🔹 Asegurar que se recibe
    createClassViewModel: CreateClassViewModel,
    createActivityViewModel: CreateActivityViewModel

) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginUi(viewModel = loginViewModel, navController)
        }
        composable("register") {
            RegisterUi(viewModel = registerViewModel, navController)
        }
        composable("view_classes") {
            ViewTaskUi(viewModel = viewTasksViewModel, navController, userPreferences) // 🔹 Pasar `userPreferences`
        }
        composable("activities_screen/{idClass}") { backStackEntry ->
            val idClass = backStackEntry.arguments?.getString("idClass")?.toIntOrNull() ?: 0
            ViewActivitiesScreen(
                viewModel = viewActivitiesViewModel,
                navController = navController,
                userPreferences = userPreferences,
                idClass = idClass
            )
        }

        composable("create_activity/{idClass}") { backStackEntry ->
            val idClass = backStackEntry.arguments?.getString("idClass")?.toIntOrNull() ?: 0
            CreateActivityScreen(
                viewModel = createActivityViewModel,
                navController = navController,
                userPreferences = userPreferences,
                idClass = idClass
            )
        }

        composable("create_class") {
            CreateClassScreen(
                viewModel = createClassViewModel,
                navController = navController,
                userPreferences = userPreferences
            )
        }


    }
}
