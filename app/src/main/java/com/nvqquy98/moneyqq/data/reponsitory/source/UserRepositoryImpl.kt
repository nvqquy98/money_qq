package com.nvqquy98.moneyqq.data.reponsitory.source

import com.nvqquy98.moneyqq.data.model.UserData
import com.nvqquy98.moneyqq.data.reponsitory.source.local.UserLocalDataSource
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.UserRemoteDataSource
import com.nvqquy98.moneyqq.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun login(email: String, password: String) {
        userRemoteDataSource.login(email = email, password = password).also {
            userLocalDataSource.saveAccessToken(it.accessToken)
            userLocalDataSource.saveUserData(it.userData)
        }
    }

    override suspend fun register(name: String, email: String, password: String) {
        userRemoteDataSource.register(name = name, email = email, password = password).also {
            userLocalDataSource.saveAccessToken(it.accessToken)
            userLocalDataSource.saveUserData(it.userData)
        }
    }

    override fun getUserInfo(): UserData? {
        return userLocalDataSource.getUserInfo()
    }

    override fun logout() {
        userLocalDataSource.logout()
    }
}