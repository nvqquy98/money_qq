package com.nvqquy98.moneyqq.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserData(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("email")
    val email: String
) : BaseData()