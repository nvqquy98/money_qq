package com.nvqquy98.moneyqq.mainapp

import com.nvqquy98.moneyqq.domain.usecase.user.GetUserInfoUseCase
import com.nvqquy98.moneyqq.presentation.ui.base.BaseViewModel

class MainViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : BaseViewModel() {
    val userInfo get() = getUserInfoUseCase()
}
