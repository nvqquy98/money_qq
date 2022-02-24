package com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(@Expose @SerializedName("data") val data: T)
