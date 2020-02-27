package tools.di

import com.movies.di.AppModule
import com.movies.di.Injector
import com.movies.di.PmModule
import dagger.Component
import tools.TestDependencies
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    PmModule::class,
    TestServiceModule::class
])
interface TestAppComponent : Injector {
    fun inject(deps: TestDependencies)
}