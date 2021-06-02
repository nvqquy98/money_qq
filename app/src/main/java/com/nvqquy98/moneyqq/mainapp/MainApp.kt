package com.nvqquy98.moneyqq.mainapp

import android.app.Application
import com.nvqquy98.moneyqq.BuildConfig
import com.nvqquy98.moneyqq.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@MainApp)
            modules(
                listOf(
                    apiModule,
                    appModule,
                    mapperModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
        instance = this
    }

    companion object {
        lateinit var instance: MainApp
            private set
    }
}
