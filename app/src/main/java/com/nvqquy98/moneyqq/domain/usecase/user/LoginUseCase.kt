package com.nvqquy98.moneyqq.domain.usecase.user

import com.nvqquy98.moneyqq.domain.repository.UserRepository
import com.nvqquy98.moneyqq.domain.usecase.base.BaseCancelableUseCase
import com.nvqquy98.moneyqq.domain.usecase.base.BaseInput

class LoginUseCase(private val userRepository: UserRepository) :
    BaseCancelableUseCase<LoginUseCase.Input, Unit>() {
    data class Input(val email: String, val password: String) : BaseInput

    override suspend fun buildUseCase(input: Input) {
        return userRepository.login(email = input.email, password = input.password)
    }
}