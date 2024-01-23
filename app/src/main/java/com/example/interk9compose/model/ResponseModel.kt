package com.example.interk9compose.model

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
    @SerializedName("Data")
    val `data`: T,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Success")
    var success: Boolean,
)
