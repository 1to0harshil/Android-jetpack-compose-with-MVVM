package com.example.interk9compose.repository

import com.example.interk9compose.networkModule.RetrofitInstance
import com.example.interk9compose.dataClasses.Login
import com.example.interk9compose.dataClasses.LoginReq
import com.example.interk9compose.model.ResponseModel
import retrofit2.Response

class Repository {

    private val loginService = RetrofitInstance.projectServices

    suspend fun Login(login : LoginReq): Response<ResponseModel<Login>> {
        return loginService.getLogIn(login)
    }

}