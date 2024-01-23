package com.example.ComposeWithMVVM.dataClasses

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("userId")var userId: Int? = null,
    @SerializedName("firstName")var firstName: String? = null,
    @SerializedName("lastName")var lastName: String? = null,
    @SerializedName("email")var email: String? = null,
    @SerializedName("password")var password: String? = null,
    @SerializedName("roleId")var roleId: Int? = null,
    @SerializedName("positionId")var positionId: Int? = null,
    @SerializedName("positionName")var positionName: String? = null,
    @SerializedName("userPhoto")var userPhoto: String? = null,
    @SerializedName("organizationId")var organizationId: String? = null,
    @SerializedName("logoUrl")var logoUrl: String? = null,
    @SerializedName("JWTToken")var JWTToken: String? = null,
    @SerializedName("error")var error: String? = null,
    @SerializedName("deviceType")var deviceType: String? = null,
    @SerializedName("deviceModel")var deviceModel: String? = null,
    @SerializedName("deviceToken")var deviceToken: String? = null,
    @SerializedName("deviceVersion")var deviceVersion: String? = null

)