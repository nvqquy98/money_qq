package com.nvqquy98.moneyqq.data.reponsitory.source.remote.api

import com.google.gson.Gson
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private const val CONNECT_TIMEOUT = 30000L
    private const val READ_TIMEOUT = 30000L
    private const val WRITE_TIMEOUT = 3000L

    fun <T> generate(
        baseUrl: String,
        serviceClass: Class<T>,
        gson: Gson,
        authenticator: Authenticator?,
        vararg interceptor: Interceptor
    ): T {
        val okHttpBuilder = OkHttpClient().newBuilder()
        for (itr in interceptor) {
            okHttpBuilder.addInterceptor(itr)
        }
        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        if (authenticator != null) {
            okHttpBuilder.authenticator(authenticator)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()
        return retrofit.create(serviceClass)
    }
}
