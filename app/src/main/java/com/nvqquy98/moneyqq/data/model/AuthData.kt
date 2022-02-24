package com.nvqquy98.moneyqq.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthData(
    @Expose @SerializedName("user") val userData: UserData,
    @Expose @SerializedName("access_token") val accessToken: String
) : BaseData()