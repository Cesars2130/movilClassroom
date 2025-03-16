package com.example.classapp.src.viewTasks.data.datasource

import com.example.classapp.src.viewTasks.data.datasource.ViewTaskService
import com.example.classapp.core.petitions.RetrofitClient


object RetrofitClientViewTask {

    val api: ViewTaskService by lazy {
        RetrofitClient.instance.create(ViewTaskService::class.java)
    }
}