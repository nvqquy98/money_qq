package com.nvqquy98.moneyqq.data.reponsitory.source.remote.api

import com.nvqquy98.moneyqq.data.model.AuthData
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.request.LoginRequest
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.request.RegisterRequest
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NonAuthApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): BaseResponse<AuthData>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): BaseResponse<AuthData>
}
