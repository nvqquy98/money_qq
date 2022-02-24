package com.nvqquy98.moneyqq.domain.usecase.user

import com.nvqquy98.moneyqq.domain.repository.UserRepository
import com.nvqquy98.moneyqq.domain.usecase.base.BaseDirectUseCase
import com.nvqquy98.moneyqq.domain.usecase.base.EmptyInput

class LogoutUseCase(private val userRepository: UserRepository):BaseDirectUseCase<EmptyInput,Unit>(){
    override fun buildUseCase(input: EmptyInput) {
        return userRepository.logout()
    }
}