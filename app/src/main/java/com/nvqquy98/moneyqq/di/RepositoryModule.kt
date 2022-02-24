package com.nvqquy98.moneyqq.di

import com.nvqquy98.moneyqq.data.reponsitory.source.UserRepositoryImpl
import com.nvqquy98.moneyqq.data.reponsitory.source.local.UserLocalDataSource
import com.nvqquy98.moneyqq.data.reponsitory.source.local.api.SharedPrefApi
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.UserRemoteDataSource
import com.nvqquy98.moneyqq.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SharedPrefApi(get(), get()) }
    single { UserLocalDataSource(get()) }
    single { UserRemoteDataSource(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

}