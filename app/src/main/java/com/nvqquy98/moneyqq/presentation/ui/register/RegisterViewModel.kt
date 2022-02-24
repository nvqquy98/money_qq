package com.nvqquy98.moneyqq.presentation.ui.register

import com.nvqquy98.moneyqq.domain.usecase.user.RegisterUseCase
import com.nvqquy98.moneyqq.presentation.ui.base.BaseViewModel
import com.nvqquy98.moneyqq.util.livedata.SingleLiveEvent

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : BaseViewModel() {
    val registerSuccess = SingleLiveEvent<Unit>()
    val registerError = SingleLiveEvent<String>()

    fun registerUser(name: String, email: String, password: String) {
        launchUseCase(
            registerUseCase,
            RegisterUseCase.Input(name = name, email = email, password = password),
            onError = { registerError.postValue(it.message) },
            onSuccess = { registerSuccess.postValue(Unit) })
    }
}