package com.nvqquy98.moneyqq.di

import android.content.Context
import com.nvqquy98.moneyqq.data.dispatcher.DefaultDispatcher
import com.nvqquy98.moneyqq.data.dispatcher.IODispatcher
import com.nvqquy98.moneyqq.data.dispatcher.MainDispatcher
import com.nvqquy98.moneyqq.domain.dispatcher.DispatcherProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module() {
    single { getResource(get()) }
    single<DispatcherProvider>(named("main")) { MainDispatcher() }
    single<DispatcherProvider>(named("io")) { IODispatcher() }
    single<DispatcherProvider>(named("default")) { DefaultDispatcher() }
}

private fun getResource(context: Context) = context.resources
