package com.example.classapp.src.register.data.model

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
    val surname: String,
    val last_name: String,
    val id_role: Int
)