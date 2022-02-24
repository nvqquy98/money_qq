package com.nvqquy98.moneyqq.domain.usecase.user

import com.nvqquy98.moneyqq.domain.repository.UserRepository
import com.nvqquy98.moneyqq.domain.usecase.base.BaseCancelableUseCase
import com.nvqquy98.moneyqq.domain.usecase.base.BaseInput

class RegisterUseCase(private val userRepository: UserRepository) :
    BaseCancelableUseCase<RegisterUseCase.Input, Unit>() {
    data class Input(val name: String, val email: String, val password: String) : BaseInput

    override suspend fun buildUseCase(input: Input) {
        return userRepository.register(input.name, input.email, input.password)
    }
}