package com.nvqquy98.moneyqq.data.reponsitory.source.local

import com.nvqquy98.moneyqq.data.model.UserData
import com.nvqquy98.moneyqq.data.reponsitory.source.local.api.SharedPrefApi
import com.nvqquy98.moneyqq.data.reponsitory.source.local.api.pref.SharedPrefKey

class UserLocalDataSource(private val sharedPrefApi: SharedPrefApi) {
    fun saveAccessToken(accessToken: String) {
        sharedPrefApi[SharedPrefKey.ACCESS_TOKEN] = accessToken
    }

    fun saveUserData(userData: UserData) {
        sharedPrefApi[SharedPrefKey.CURRENT_USER] = userData
    }

    fun getUserInfo(): UserData? = sharedPrefApi[SharedPrefKey.CURRENT_USER]

    fun logout() {
        sharedPrefApi.removeKey(SharedPrefKey.CURRENT_USER, SharedPrefKey.ACCESS_TOKEN)
    }
}