package com.example.classapp.src.createClass.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classapp.src.createClass.domain.CreateClassUseCase
import kotlinx.coroutines.launch

class CreateClassViewModel(private val createClassUseCase: CreateClassUseCase) : ViewModel() {

    suspend fun createClass(userId: Int, className: String, classCode: Int): Boolean {
        return try {
            println("🔄 Enviando datos al UseCase: userId=$userId, className=$className, classCode=$classCode")

            createClassUseCase.execute(userId, className, classCode)
        } catch (e: Exception) {
            false
        }
    }
}
