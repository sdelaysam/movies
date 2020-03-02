package tools

import com.movies.App
import com.movies.di.AppModule
import timber.log.Timber
import tools.di.DaggerTestAppComponent
import tools.di.TestAppComponent
import tools.log.TimberPrintTree

open class IntegrationTestApp : App() {

    lateinit var testComponent: TestAppComponent

    override val injector
        get() = testComponent

    override fun onCreate() {
        super.onCreate()
        testComponent = DaggerTestAppComponent.builder().appModule(AppModule(this)).build()
    }
    
    override fun initLogger() {
        Timber.plant(TimberPrintTree())
    }

    override fun initPicasso() {
        // on-op
    }
}