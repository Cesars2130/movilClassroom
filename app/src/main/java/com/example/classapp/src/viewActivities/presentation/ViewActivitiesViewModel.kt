package com.example.classapp.src.viewActivities.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classapp.src.viewActivities.data.model.ActivityDTO
import com.example.classapp.src.viewActivities.domain.ViewActivitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewActivitiesViewModel(private val useCase: ViewActivitiesUseCase) : ViewModel() {
    private val _activities = MutableStateFlow<List<ActivityDTO>>(emptyList())
    val activities: StateFlow<List<ActivityDTO>> = _activities

    fun loadActivities(id_class: Int) {
        viewModelScope.launch {
            _activities.value = useCase.execute(id_class)
        }
    }
}
