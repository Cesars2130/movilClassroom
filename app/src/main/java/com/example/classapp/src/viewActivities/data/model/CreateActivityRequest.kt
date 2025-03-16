package com.example.classapp.src.viewActivities.data.model

data class CreateActivityRequest(
    val activity_name: String,
    val activity_description: String,
    val id_class: Int,
    val id_user: Int
)
