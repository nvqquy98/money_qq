package com.nvqquy98.moneyqq.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nvqquy98.moneyqq.BuildConfig
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.NonAuthApi
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.ServiceGenerator
import com.nvqquy98.moneyqq.data.reponsitory.source.remote.api.config.ApiConfig
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


val apiModule = module() {
    single { buildGson() }
    single { buildNoneAuthApi(get()) }
}

fun buildGson(): Gson {
    return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
}

private fun buildNoneAuthApi(gson: Gson): NonAuthApi {
    return ServiceGenerator.generate(
        ApiConfig.BASE_URL,
        NonAuthApi::class.java,
        gson,
        null,
        buildHttpLog()
    )
}

private fun buildHttpLog(): HttpLoggingInterceptor {
    val logLevel =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return HttpLoggingInterceptor().setLevel(logLevel)
}
