package com.example.classapp.src.viewActivities.data.datasource

import com.example.classapp.src.viewActivities.data.model.ActivityDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViewActivitiesService {
    @GET("/activities/{id_class}")
    suspend fun getActivitiesByClass(@Path("id_class") id_class: Int): Response<List<ActivityDTO>>
}
