package com.nvqquy98.moneyqq.presentation.ui.login

import com.nvqquy98.moneyqq.domain.usecase.user.LoginUseCase
import com.nvqquy98.moneyqq.presentation.ui.base.BaseViewModel
import com.nvqquy98.moneyqq.util.livedata.SingleLiveEvent

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {
    val loginSuccess = SingleLiveEvent<Unit>()
    val loginError = SingleLiveEvent<String>()

    fun login(email: String, password: String) {
        launchUseCase(
            loginUseCase,
            LoginUseCase.Input(email = email, password = password),
            onError = { loginError.postValue(it.message) },
            onSuccess = { loginSuccess.postValue(Unit) })
    }
}