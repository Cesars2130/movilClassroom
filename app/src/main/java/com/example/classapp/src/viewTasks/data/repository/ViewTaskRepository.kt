package com.example.classapp.src.viewTasks.data.repository

import com.example.classapp.core.petitions.RetrofitClient
import com.example.classapp.src.viewTasks.data.datasource.ViewTaskService
import com.example.classapp.src.viewTasks.data.model.JoinClassRequest
import com.example.classapp.src.viewTasks.data.model.ViewTaskDTO

class ViewTaskRepository {
    private val api = RetrofitClient.instance.create(ViewTaskService::class.java)

    suspend fun getClassesByUserId(idUser: Int): List<ViewTaskDTO> {
        return try {
            val response = api.getClassesByUserId(idUser)
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                println("⚠ Error en la API: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            println("⚠ Error de conexión: ${e.message}")
            emptyList()
        }
    }
    suspend fun joinClass(idUser: Int, classCode: String): Boolean {
        return try {
            val response = api.joinClass(JoinClassRequest(idUser, classCode))

            // 🔹 Aceptamos respuestas con códigos 2XX
            if (response.code() in 200..299) {
                println("✅ Usuario unido a la clase con éxito")
                true
            } else {
                println("⚠ Error en API: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            println("⚠ Error en joinClass: ${e.message}")
            false
        }
    }
}
