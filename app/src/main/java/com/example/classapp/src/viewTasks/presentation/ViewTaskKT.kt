package com.example.classapp.src.viewTasks.presentation

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
import com.example.classapp.src.viewTasks.data.model.ViewTaskDTO
import kotlinx.coroutines.launch

@Composable
fun ViewTaskUi(viewModel: ViewTaskViewModel, navController: NavController, userPreferences: UserPreferences) {
    val classes by viewModel.tasks.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    // Estado para ingresar a una clase
    var classCode by remember { mutableStateOf("") }
    var showJoinInput by remember { mutableStateOf(false) }
    var joinError by remember { mutableStateOf<String?>(null) }
    var userRole by remember { mutableStateOf(0) }

    // Obtener el rol del usuario
    LaunchedEffect(Unit) {
        userRole = userPreferences.getUserData()["id_role"] as? Int ?: 0
    }

    LaunchedEffect(userRole) { // 🔹 Ejecutar solo cuando `userRole` cambia
        viewModel.loadClasses()
    }


    val principalColor = Color(0xFF3674B5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(principalColor)
    ) {
        // 🔹 Barra superior simplificada
        SimpleAppBar(navController, userRole)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 🔹 Botón para unirse a una clase
            Button(
                onClick = { showJoinInput = !showJoinInput },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (showJoinInput) "Cancelar" else "Unirse a una Clase")
            }

            // 🔹 Input para ingresar el código de la clase
            if (showJoinInput) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = classCode,
                        onValueChange = { classCode = it },
                        label = { Text("Código de la Clase") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val success = viewModel.joinClass(classCode)
                                if (success) {
                                    showJoinInput = false
                                    classCode = ""
                                    viewModel.loadClasses() // 🔹 Recargar la lista de clases después de unirse
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Unirse")
                    }

                    joinError?.let {
                        Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(classes) { classItem ->
                    ClassCard(classItem, navController)
                }
            }
        }
    }
}

@Composable
fun SimpleAppBar(navController: NavController, userRole: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Mis Clases",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Row {
            if (userRole == 2) { // 🔹 Solo profesores pueden crear clases
                Button(
                    onClick = { navController.navigate("create_class") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Agregar Clase", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Salir", color = Color.Black)
            }
        }
    }
}

@Composable
fun ClassCard(classItem: ViewTaskDTO, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navController.navigate("activities_screen/${classItem.id_class}")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = classItem.class_name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Código: ${classItem.class_code}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}
