package com.example.classapp.src.viewTasks.data.datasource

import com.example.classapp.src.viewTasks.data.model.JoinClassRequest
import com.example.classapp.src.viewTasks.data.model.ViewTaskDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ViewTaskService {
    @GET("/enrollment/{id_user}")
    suspend fun getClassesByUserId(@Path("id_user") idUser: Int): Response<List<ViewTaskDTO>>

    @POST("/enrollment/joinClass")
    suspend fun joinClass(@Body request: JoinClassRequest): Response<Void> // 🔹 Ahora Retrofit reconoce el parámetro
}
