package com.example.classapp.src.viewActivities.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classapp.core.storage.UserPreferences
import com.example.classapp.src.viewActivities.data.model.ActivityDTO
import kotlinx.coroutines.launch

@Composable
fun ViewActivitiesScreen(viewModel: ViewActivitiesViewModel, navController: NavController, userPreferences: UserPreferences, idClass: Int) {
    val activities by viewModel.activities.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var userRole by remember { mutableStateOf(0) }

    // Obtener el rol del usuario
    LaunchedEffect(Unit) {
        userRole = userPreferences.getUserData()["id_role"] as? Int ?: 0
    }

    LaunchedEffect(idClass) {
        viewModel.loadActivities(idClass) // Cargar actividades de la clase seleccionada
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3674B5))
            .padding(16.dp)
    ) {
        // 🔹 Título de la clase
        Text(
            text = "Actividades",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Botón para agregar actividad (solo si es profesor)
        if (userRole == 2) {
            Button(
                onClick = { navController.navigate("create_activity/$idClass") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Agregar Actividad", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(activities) { activity ->
                ActivityCard(activity)
            }
        }
    }
}

@Composable
fun ActivityCard(activity: ActivityDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = activity.activity_name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = activity.activity_description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}
