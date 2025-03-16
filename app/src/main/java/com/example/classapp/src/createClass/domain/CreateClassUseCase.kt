package com.example.classapp.src.createClass.domain

import com.example.classapp.src.createClass.data.CreateClassRepository

class CreateClassUseCase(private val repository: CreateClassRepository) {
    suspend fun execute(userId: Int, className: String, classCode: Int): Boolean {
        return repository.createClass(userId, className, classCode)
    }
}
