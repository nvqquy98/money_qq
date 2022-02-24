package com.nvqquy98.moneyqq.di

import com.nvqquy98.moneyqq.mainapp.MainViewModel
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import com.nvqquy98.moneyqq.presentation.ui.login.LoginViewModel
import com.nvqquy98.moneyqq.presentation.ui.main.HomeViewModel
import com.nvqquy98.moneyqq.presentation.ui.register.RegisterViewModel
import com.nvqquy98.moneyqq.presentation.ui.resetpassword.ResetPasswordViewModel
import com.nvqquy98.moneyqq.presentation.ui.sendemail.SendEmailViewModel
import com.nvqquy98.moneyqq.presentation.ui.top.TopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EmptyViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { TopViewModel() }
    viewModel { SendEmailViewModel() }
    viewModel { ResetPasswordViewModel() }
}
