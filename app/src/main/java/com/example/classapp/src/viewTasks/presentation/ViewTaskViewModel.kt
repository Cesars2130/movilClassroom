package com.example.classapp.src.viewTasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classapp.core.storage.UserPreferences
import com.example.classapp.src.viewTasks.data.model.ViewTaskDTO
import com.example.classapp.src.viewTasks.domain.ViewTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewTaskViewModel(
    private val useCase: ViewTaskUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<ViewTaskDTO>>(emptyList())
    val tasks: StateFlow<List<ViewTaskDTO>> = _tasks

    fun loadClasses() {
        viewModelScope.launch {
            val idUser = userPreferences.getUserId() ?: return@launch
            val result = useCase.execute(idUser)

            if (result.isNotEmpty()) {
                _tasks.value = result
            } else {
                println("⚠ No se encontraron clases para el usuario con ID: $idUser")
            }
        }
    }

    fun joinClass(classCode: String): Boolean {
        var result = false
        viewModelScope.launch {
            val idUser = userPreferences.getUserId() ?: return@launch
            result = useCase.joinClass(idUser, classCode)

            if (result) {
                loadClasses() // 🔹 Recargar la lista de clases después de unirse
            }
        }
        return result
    }

}
