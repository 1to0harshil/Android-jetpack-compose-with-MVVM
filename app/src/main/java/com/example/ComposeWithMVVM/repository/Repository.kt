package com.example.ComposeWithMVVM.repository

import com.example.ComposeWithMVVM.networkModule.RetrofitInstance
import com.example.ComposeWithMVVM.dataClasses.Login
import com.example.ComposeWithMVVM.dataClasses.LoginReq
import com.example.ComposeWithMVVM.model.ResponseModel
import retrofit2.Response

class Repository {

    private val loginService = RetrofitInstance.projectServices

    suspend fun Login(login : LoginReq): Response<ResponseModel<Login>> {
        return loginService.getLogIn(login)
    }

}