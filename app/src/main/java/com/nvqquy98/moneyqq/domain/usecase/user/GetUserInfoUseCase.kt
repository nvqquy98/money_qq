package com.nvqquy98.moneyqq.domain.usecase.user

import com.nvqquy98.moneyqq.data.model.UserData
import com.nvqquy98.moneyqq.domain.repository.UserRepository
import com.nvqquy98.moneyqq.domain.usecase.base.BaseDirectUseCase
import com.nvqquy98.moneyqq.domain.usecase.base.EmptyInput

class GetUserInfoUseCase(private val userRepository: UserRepository) :
    BaseDirectUseCase<EmptyInput, UserData?>() {
    override fun buildUseCase(input: EmptyInput): UserData? {
        return userRepository.getUserInfo()
    }
}