package com.example.classapp.src.viewActivities.data.repository

import com.example.classapp.src.viewActivities.data.datasource.RetrofitClientCreateActivity
import com.example.classapp.src.viewActivities.data.model.CreateActivityRequest

class CreateActivityRepository {
    private val api = RetrofitClientCreateActivity.api

    suspend fun createActivity(activityName: String, activityDescription: String, idClass: Int, idUser: Int): Boolean {
        return try {
            val response = api.createActivity(CreateActivityRequest(activityName, activityDescription, idClass, idUser))
            response.isSuccessful // Verifica si la petición fue exitosa
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
