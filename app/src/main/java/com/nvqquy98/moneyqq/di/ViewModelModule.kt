package com.nvqquy98.moneyqq.di

import com.nvqquy98.moneyqq.mainapp.MainViewModel
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EmptyViewModel() }
    viewModel { MainViewModel() }
}
