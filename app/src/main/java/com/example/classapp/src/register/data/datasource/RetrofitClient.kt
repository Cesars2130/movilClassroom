package com.example.classapp.src.register.data.datasource

import com.example.classapp.core.petitions.RetrofitClient

object RetrofitClient {

    val api: RegisterService by lazy {
        RetrofitClient.instance.create(RegisterService::class.java)
    }
}
