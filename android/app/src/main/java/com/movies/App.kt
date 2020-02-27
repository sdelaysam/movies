package com.movies

import android.app.Application
import android.util.Log
import com.movies.api.Api
import com.movies.di.AppModule
import com.movies.di.DaggerAppComponent
import com.movies.di.Injector
import timber.log.Timber

open class App: Application() {

    open val injector: Injector by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.uprootAll()
    }

    protected open fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

val Application.injector: Injector
    get() = (this as App).injector