package com.movies

import android.app.Application
import com.movies.di.AppModule
import com.movies.di.DaggerAppComponent
import com.movies.di.Injector
import com.movies.util.picasso.PicassoUtil.initPicasso
import timber.log.Timber

open class App: Application() {

    open val injector: Injector by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initPicasso()
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

    protected open fun initPicasso() {
        initPicasso(this)
    }
}

val Application.injector: Injector
    get() = (this as App).injector