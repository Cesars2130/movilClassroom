package com.example.classapp.src.viewTasks.domain

import com.example.classapp.src.viewTasks.data.model.ViewTaskDTO
import com.example.classapp.src.viewTasks.data.repository.ViewTaskRepository

class ViewTaskUseCase(private val repository: ViewTaskRepository) {
    suspend fun execute(idUser: Int): List<ViewTaskDTO> {
        return repository.getClassesByUserId(idUser)
    }

    suspend fun joinClass(id_user: Int, class_code: String): Boolean {
        return repository.joinClass(id_user, class_code)
    }

}
