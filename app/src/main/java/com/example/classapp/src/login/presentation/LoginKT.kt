package com.example.classapp.src.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun LoginUi(viewModel: LoginViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginSuccess by viewModel.loginSuccess

    // Definir los colores verdes solicitados
    val Principalcolor = Color(0xFF3674B5)
    // Un tono complementario para el degradado (puedes ajustar este color)
    val secondaryGreen = Color(0xFF3674B5)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Principalcolor),
        contentAlignment = Alignment.Center
    ) {
        // Tarjeta que contiene el formulario de inicio de sesión
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Logo y título
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Logo",
                    tint = Principalcolor,
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Bienvenido",
                    style = MaterialTheme.typography.headlineSmall
                )
                // Campo de texto para el usuario (con fondo blanco y bordes redondeados)
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Ícono de usuario"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),

                    )
                // Campo de texto para la contraseña (siempre enmascarada)
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Ícono de contraseña"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),

                    )
                // Botón de "Ingresar"
                Button(
                    onClick = {
                        viewModel.loginUser(
                            email = email,
                            password = password,
                            onSuccess = { navController.navigate("home") },  // 🚀 Redirigir a la pantalla principal
                            onError = { errorMessage -> println("Error: $errorMessage") } // 🚨 Manejar errores
                        )
                    },
                    enabled = email.isNotEmpty() && password.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Principalcolor)
                ) {
                    Text(text = "Ingresar")
                }
                // Botón de "Crear Cuenta"
                OutlinedButton(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    Text(text = "Crear cuenta")
                }

            }
        }
    }
    Text(
        text = "Taskroom",
        color = Color.White,
        style = TextStyle(
            fontSize = 38.sp, // Tamaño más grande
            fontWeight = FontWeight.ExtraBold, // Texto más grueso
            textAlign = TextAlign.Center,
            shadow = Shadow( // Aplicar sombra correctamente
                color = Color.Black,
                offset = Offset(4f, 4f), // Desplazamiento de la sombra
                blurRadius = 8f // Difuminado de la sombra
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp)
    )


    // Navegación al cambiar el estado de loginSuccess
    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            navController.navigate("")
        }
        viewModel.resetLoginState()
    }
}
