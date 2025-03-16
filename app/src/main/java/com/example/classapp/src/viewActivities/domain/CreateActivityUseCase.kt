package com.example.classapp.src.viewActivities.domain

import com.example.classapp.src.viewActivities.data.repository.CreateActivityRepository

class CreateActivityUseCase(private val repository: CreateActivityRepository) {
    suspend fun execute(activityName: String, activityDescription: String, idClass: Int, idUser: Int): Boolean {
        return repository.createActivity(activityName, activityDescription, idClass, idUser)
    }
}
