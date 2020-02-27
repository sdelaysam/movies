package com.movies.di

import dagger.Component
import md.medici.util.di.pm.PmFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ServiceModule::class,
    PmModule::class
])
interface AppComponent : Injector

interface Injector {
    fun pmFactory(): PmFactory
}