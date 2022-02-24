package com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("email") val email: String,
    @Expose @SerializedName("password") val password: String
)