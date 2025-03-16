package com.example.classapp.src.viewActivities.domain

import com.example.classapp.src.viewActivities.data.model.ActivityDTO
import com.example.classapp.src.viewActivities.data.repository.ViewActivitiesRepository

class ViewActivitiesUseCase(private val repository: ViewActivitiesRepository) {
    suspend fun execute(id_class: Int): List<ActivityDTO> {
        return repository.getActivitiesByClass(id_class)
    }
}
