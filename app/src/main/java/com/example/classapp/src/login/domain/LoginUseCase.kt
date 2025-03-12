package com.example.classapp.src.login.domain

import com.example.classapp.src.login.data.model.LoginResponse
import com.example.classapp.src.login.data.repository.LoginRepository
import retrofit2.Response

class LoginUseCase(private val repository: LoginRepository) {
    suspend fun execute(email: String, password: String): Response<LoginResponse> {
        return repository.loginUser(email, password)
    }
}
