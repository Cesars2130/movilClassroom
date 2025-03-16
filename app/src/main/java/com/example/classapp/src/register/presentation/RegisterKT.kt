package com.example.classapp.src.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterUi(
    viewModel: RegisterViewModel,
    navController: NavController
) {
    val name by viewModel.name.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val surname by viewModel.surname.observeAsState("")
    val lastName by viewModel.lastName.observeAsState("")
    val idRole by viewModel.idRole.observeAsState(2) // 2 = Alumno, 1 = Profesor
    val registrationResult by viewModel.registrationResult.observeAsState()
    var isPasswordVisible by remember { mutableStateOf(false) }

    val primaryGreen = Color(0xFF71C55B)
    val secondaryGreen = Color(0xFF8ACD75)

    LaunchedEffect(registrationResult) {
        when (registrationResult) {
            is RegistrationResult.Success -> {
                navController.navigate("login")
                viewModel.resetRegistrationResult()
                viewModel.resetRegistrationForm()
            }
            is RegistrationResult.Error -> {
                val errorMessage = (registrationResult as RegistrationResult.Error).message
                println("Error en el registro: $errorMessage") // Mensaje de error en consola
            }
            null -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(primaryGreen, secondaryGreen)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
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
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Logo", tint = primaryGreen, modifier = Modifier.size(72.dp))
                Text(text = "Registrarse", style = MaterialTheme.typography.headlineSmall)

                TextField(value = name, onValueChange = { viewModel.onNameChanged(it) }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
                TextField(value = email, onValueChange = { viewModel.onEmailChanged(it) }, label = { Text("Correo Electrónico") }, modifier = Modifier.fillMaxWidth())
                TextField(value = surname, onValueChange = { viewModel.onSurnameChanged(it) }, label = { Text("Apellido Paterno") }, modifier = Modifier.fillMaxWidth())
                TextField(value = lastName, onValueChange = { viewModel.onLastNameChanged(it) }, label = { Text("Apellido Materno") }, modifier = Modifier.fillMaxWidth())

                // RADIO BUTTONS PARA SELECCIONAR EL ROL
                Text(text = "Selecciona tu rol:", style = MaterialTheme.typography.bodyLarge)
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = idRole == 1,
                            onClick = { viewModel.onRoleChanged(1) }
                        )
                        Text(text = "Alumno", modifier = Modifier.padding(start = 8.dp))
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = idRole == 2,
                            onClick = { viewModel.onRoleChanged(2) }
                        )
                        Text(text = "Profesor", modifier = Modifier.padding(start = 8.dp))
                    }
                }

                TextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Contraseña") },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = if (isPasswordVisible) Icons.Default.Check else Icons.Default.Close, contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = { viewModel.registerUser() }, enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty(), modifier = Modifier.fillMaxWidth().height(50.dp)) {
                    Text(text = "Registrarse")
                }
            }
        }
    }
}
