package com.example.classapp.src.viewActivities.data.repository

import com.example.classapp.core.petitions.RetrofitClient
import com.example.classapp.src.viewActivities.data.datasource.ViewActivitiesService
import com.example.classapp.src.viewActivities.data.model.ActivityDTO

class ViewActivitiesRepository {
    private val api = RetrofitClient.instance.create(ViewActivitiesService::class.java) // 🔹 Usar RetrofitClient centralizado

    suspend fun getActivitiesByClass(idClass: Int): List<ActivityDTO> {
        val response = api.getActivitiesByClass(idClass)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}
