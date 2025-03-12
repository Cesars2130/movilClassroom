package com.example.classapp.src.login.data.datasource

import com.example.classapp.src.login.data.model.LoginDTO
import com.example.classapp.src.login.data.model.LoginRequest
import com.example.classapp.src.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/users/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>
}