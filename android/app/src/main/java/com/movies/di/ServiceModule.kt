package com.movies.di

import com.movies.api.Api
import com.movies.data.service.ApiService
import com.movies.data.service.DefaultApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        System.loadLibrary("android-lib")
        return DefaultApiService(Api.create()!!)
    }
}