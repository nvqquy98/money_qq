package com.nvqquy98.moneyqq.domain.repository

import com.nvqquy98.moneyqq.data.model.UserData


interface UserRepository {
    suspend fun login(email: String, password: String)

    suspend fun register(name: String, email: String, password: String)

    fun getUserInfo(): UserData?

    fun logout()
}