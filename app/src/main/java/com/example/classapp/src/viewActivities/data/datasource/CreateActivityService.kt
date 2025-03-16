package com.example.classapp.src.viewActivities.data.datasource

import com.example.classapp.src.viewActivities.data.model.CreateActivityRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateActivityService {
    @POST("/activities") // 📌 Ajusta esta ruta según tu API
    suspend fun createActivity(@Body request: CreateActivityRequest): Response<Unit>
}
