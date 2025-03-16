package com.example.classapp.src.viewActivities.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classapp.core.storage.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun CreateActivityScreen(viewModel: CreateActivityViewModel, navController: NavController, userPreferences: UserPreferences, idClass: Int) {
    val coroutineScope = rememberCoroutineScope()
    var activityName by remember { mutableStateOf("") }
    var activityDescription by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3674B5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear Actividad", style = MaterialTheme.typography.headlineMedium, color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = activityName,
            onValueChange = { activityName = it },
            label = { Text("Nombre de la Actividad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = activityDescription,
            onValueChange = { activityDescription = it },
            label = { Text("Descripción de la Actividad") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    val userId = userPreferences.getUserData()["id_user"] as? Int ?: return@launch
                    val success = viewModel.createActivity(activityName, activityDescription, idClass, userId)
                    if (success) {
                        navController.popBackStack()
                    } else {
                        errorMessage = "Error al crear la actividad. Inténtalo nuevamente."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Actividad")
        }

        errorMessage?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
