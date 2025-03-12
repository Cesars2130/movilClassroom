package com.example.classapp.src.register.data.repository

import com.example.classapp.src.register.data.datasource.RegisterService
import com.example.classapp.src.register.data.model.CreateUserRequest

class RegisterRepository(private val api: RegisterService) {
    suspend fun registerUser(name: String, email: String, password: String, surname: String, lastName: String, idRole: Int ) =
        api.registerUser(CreateUserRequest(name,email,password, surname, lastName, idRole))
}