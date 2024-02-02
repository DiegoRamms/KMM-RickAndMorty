package org.appgame.rickandmorty

import android.app.Application
import di.dataSourceModule
import di.networkModule
import di.repositoryModule
import di.shareModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            androidLogger()
            modules(shareModule, networkModule, repositoryModule, dataSourceModule, viewModelModule)

        }
    }
}