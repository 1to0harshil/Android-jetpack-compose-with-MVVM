package com.example.interk9compose.interfaces

import com.example.interk9compose.dataClasses.Login
import com.example.interk9compose.dataClasses.LoginReq
import com.example.interk9compose.model.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectServices {

    @POST("login/LoginUser")
    suspend fun getLogIn(@Body loginReq: LoginReq): Response<ResponseModel<Login>>

}