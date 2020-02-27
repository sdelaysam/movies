package tools

import android.content.Context
import androidx.annotation.CallSuper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import tools.di.TestAppComponent

@RunWith(AndroidJUnit4::class)
@Config(application = IntegrationTestApp::class, sdk = [28])
abstract class IntegrationTest {

    protected val deps = TestDependencies()

    protected val context: Context
        get() = ApplicationProvider.getApplicationContext()

    protected val testComponent: TestAppComponent
        get() = (context.applicationContext as IntegrationTestApp).testComponent

    protected val disposables = CompositeDisposable()

    @Before
    @CallSuper
    open fun setUp() {
        inject()
        setupSchedulers()
        beforeLaunch()
    }

    @After
    @CallSuper
    open fun tearDown() {
        disposables.clear()
        resetSchedulers()
        afterTeardown()
    }

    protected open fun inject() {
        testComponent.inject(deps)
    }

    protected open fun beforeLaunch() {}

    protected open fun afterTeardown() {}

    protected fun setupSchedulers() {
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    protected fun resetSchedulers() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            // workaround for https://github.com/ReactiveX/RxJava/issues/5234
            Thread.currentThread().setUncaughtExceptionHandler { _, throwable ->
                throw throwable.cause ?: throwable
            }
        }
    }

}