package com.example.classapp.src.createClass.data.datasource

import com.example.classapp.core.petitions.RetrofitClient

object RetrofitClientCreateClass {
    val api: CreateClassService by lazy {
        RetrofitClient.instance.create(CreateClassService::class.java)
    }
}
