package com.example.ComposeWithMVVM.interfaces

import com.example.ComposeWithMVVM.dataClasses.Login
import com.example.ComposeWithMVVM.dataClasses.LoginReq
import com.example.ComposeWithMVVM.model.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectServices {

    @POST("login/LoginUser")
    suspend fun getLogIn(@Body loginReq: LoginReq): Response<ResponseModel<Login>>

}