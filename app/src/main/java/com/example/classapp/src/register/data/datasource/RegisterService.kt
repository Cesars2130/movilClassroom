package com.example.classapp.src.register.data.datasource

import com.example.classapp.src.register.data.model.CreateUserRequest
import com.example.classapp.src.register.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RegisterService {
    @POST("/users/")
    suspend fun registerUser(@Body request: CreateUserRequest): Response<UserDTO>
}