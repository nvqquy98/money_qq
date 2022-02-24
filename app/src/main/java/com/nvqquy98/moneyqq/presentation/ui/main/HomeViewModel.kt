package com.nvqquy98.moneyqq.presentation.ui.main

import com.nvqquy98.moneyqq.domain.usecase.user.LogoutUseCase
import com.nvqquy98.moneyqq.presentation.ui.base.BaseViewModel
import com.nvqquy98.moneyqq.util.livedata.SingleLiveEvent

class HomeViewModel(private val logoutUseCase: LogoutUseCase) : BaseViewModel() {
    val logoutSuccess = SingleLiveEvent<Unit>()

    fun logout() {
        logoutUseCase()
        logoutSuccess.postValue(Unit)
    }
}