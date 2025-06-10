package com.example.dogfotosproject

import android.app.Application
import com.example.dogfotosproject.di.dataModule
import com.example.dogfotosproject.di.domainModule
import com.example.dogfotosproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                dataModule,
                domainModule,
                viewModelModule
            ))
        }
    }
}