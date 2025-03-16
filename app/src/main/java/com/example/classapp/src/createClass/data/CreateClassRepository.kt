package com.example.classapp.src.createClass.data

import com.example.classapp.src.createClass.data.datasource.RetrofitClientCreateClass
import com.example.classapp.src.createClass.data.model.CreateClassRequest

class CreateClassRepository {
    private val api = RetrofitClientCreateClass.api

    suspend fun createClass(userId: Int, className: String, classCode: Int): Boolean {
        return try {
            println("🚀 Enviando petición con: userId=$userId, className=$className, classCode=$classCode")

            val response = api.createClass(CreateClassRequest(userId, className, classCode))
            if (!response.isSuccessful) {
                println("❌ Error en la API: ${response.errorBody()?.string()}")
            }
            response.isSuccessful // Verifica si la petición fue exitosa
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
