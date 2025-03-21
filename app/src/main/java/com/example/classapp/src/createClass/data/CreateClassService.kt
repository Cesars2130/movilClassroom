package com.example.classapp.src.createClass.data.datasource

import com.example.classapp.src.createClass.data.model.CreateClassRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateClassService {
    @POST("/classes") // 📌 Ajusta esta ruta según tu API
    suspend fun createClass(@Body request: CreateClassRequest): Response<Unit>
}
