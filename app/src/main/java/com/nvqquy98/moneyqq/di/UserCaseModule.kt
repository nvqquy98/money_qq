package com.nvqquy98.moneyqq.di

import com.nvqquy98.moneyqq.domain.usecase.user.GetUserInfoUseCase
import com.nvqquy98.moneyqq.domain.usecase.user.LoginUseCase
import com.nvqquy98.moneyqq.domain.usecase.user.LogoutUseCase
import com.nvqquy98.moneyqq.domain.usecase.user.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { RegisterUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { GetUserInfoUseCase(get()) }
    factory { LogoutUseCase(get()) }
}
