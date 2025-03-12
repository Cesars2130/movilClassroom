package com.example.classapp.src.register.domain

import com.example.classapp.src.register.data.repository.RegisterRepository
import com.example.classapp.src.register.data.model.UserDTO
import retrofit2.Response

class CreateUserUseCase(private val repository: RegisterRepository) {
    suspend fun execute(name: String, email: String, password: String, surname: String, lastName: String, idRole: Int): Response<UserDTO> {
        return repository.registerUser(name,email,password, surname, lastName, idRole)
    }
}