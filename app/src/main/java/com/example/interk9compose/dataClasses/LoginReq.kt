package com.example.interk9compose.dataClasses

import com.google.gson.annotations.SerializedName

data class LoginReq(
    val DeviceId: String,
    val DeviceModel: String,
    val DeviceType: String,
    val deviceToken: String,
    val deviceVersion: String,
    val FirebaseToken: String,

    @SerializedName("password")
    val Password: String,

    @SerializedName("email")
    val UserName: String,
)


