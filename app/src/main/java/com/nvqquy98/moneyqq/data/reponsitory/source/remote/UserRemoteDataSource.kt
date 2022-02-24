package com.nvqquy98.moneyqq.data.reponsitory.source.remote

import com.nvqquy98.moneyqq.data.model.AuthData
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.NonAuthApi
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.request.LoginRequest
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.request.RegisterRequest

class UserRemoteDataSource(private val nonAuthApi: NonAuthApi) {
    suspend fun login(email: String, password: String): AuthData {
        return nonAuthApi.login(LoginRequest(email = email, password = password)).data
    }

    suspend fun register(name: String, email: String, password: String): AuthData {
        return nonAuthApi.register(
            RegisterRequest(
                name = name,
                email = email,
                password = password
            )
        ).data
    }
}