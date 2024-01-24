package com.example.ComposeWithMVVM.repository

import com.example.ComposeWithMVVM.networkModule.RetrofitInstance
import com.example.ComposeWithMVVM.dataClasses.Login
import com.example.ComposeWithMVVM.dataClasses.LoginReq
import com.example.ComposeWithMVVM.interfaces.ProjectServices
import com.example.ComposeWithMVVM.model.ResponseModel
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val loginService: ProjectServices) {

    suspend fun Login(login : LoginReq): Response<ResponseModel<Login>> {
        return loginService.getLogIn(login)
    }

}