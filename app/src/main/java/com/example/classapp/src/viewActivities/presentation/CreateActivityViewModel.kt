package com.example.classapp.src.viewActivities.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classapp.src.viewActivities.domain.CreateActivityUseCase
import kotlinx.coroutines.launch

class CreateActivityViewModel(private val createActivityUseCase: CreateActivityUseCase) : ViewModel() {

    suspend fun createActivity(
        activityName: String,
        activityDescription: String,
        idClass: Int,
        idUser: Int
    ): Boolean {
        return try {
            createActivityUseCase.execute(activityName, activityDescription, idClass, idUser)
        } catch (e: Exception) {
            false
        }
    }
}
