package com.example.classapp.src.login.data.repository

import com.example.classapp.src.login.data.datasource.LoginService
import com.example.classapp.src.login.data.model.LoginRequest
import com.example.classapp.src.login.data.model.LoginResponse

import retrofit2.Response

class LoginRepository(private val api: LoginService) {
    suspend fun loginUser(email: String, password: String): Response<LoginResponse> {
        return api.loginUser(LoginRequest(email, password))
    }
}
