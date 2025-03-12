package com.example.classapp.src.login.data.datasource

import com.example.classapp.core.petitions.RetrofitClient
import com.example.classapp.src.login.data.datasource.LoginService


object RetrofitClientLogin {

    val api: LoginService by lazy {
        RetrofitClient.instance.create(LoginService::class.java)
    }
}