package com.example.classapp.src.viewActivities.data.datasource

import com.example.classapp.core.petitions.RetrofitClient

object RetrofitClientCreateActivity {
    val api: CreateActivityService by lazy {
        RetrofitClient.instance.create(CreateActivityService::class.java)
    }
}
