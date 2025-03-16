package com.example.classapp.src.viewActivities.data.model

data class ActivityDTO(
    val id_activity: Int,
    val activity_name: String,
    val activity_description: String,
    val id_class: Int // Relaciona la actividad con la clase
)
