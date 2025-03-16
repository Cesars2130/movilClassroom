package com.example.classapp.src.createClass.presentation

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
fun CreateClassScreen(viewModel: CreateClassViewModel, navController: NavController, userPreferences: UserPreferences) {
    val coroutineScope = rememberCoroutineScope()
    var className by remember { mutableStateOf("") }
    var classCodeText by remember { mutableStateOf("") }
    var classCode by remember { mutableStateOf<Int?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3674B5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear Clase", style = MaterialTheme.typography.headlineMedium, color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("Nombre de la Clase") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = classCodeText,
            onValueChange = {
                classCodeText = it
                classCode = it.toIntOrNull() // Intentar convertir a Int, si falla será null
            },
            label = { Text("Código de la Clase") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        if (classCode == null && classCodeText.isNotEmpty()) {
            Text("Ingrese un código numérico válido", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println("📝 Datos ingresados: className=$className, classCode=$classCode")
                coroutineScope.launch {
                    val userId = userPreferences.getUserData()["id_user"] as? Int ?: return@launch
                    if (classCode != null) {
                        println(userId)
                        println(classCode)
                        println(className)
                        val success = viewModel.createClass(userId, className, classCode!!)
                        println(success)
                        if (success) {
                            navController.popBackStack()
                        } else {
                            errorMessage = "Error al crear la clase. Inténtalo nuevamente."
                        }
                    } else {
                        errorMessage = "El código de la clase debe ser un número válido."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = classCode != null // Deshabilita el botón si classCode es null
        ) {
            Text("Crear Clase")
        }

        errorMessage?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
