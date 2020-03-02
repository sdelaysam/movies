package com.movies.ui.moviesList

import com.movies.api.Movie
import io.reactivex.observers.TestObserver
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.test.PmTestHelper
import org.junit.Test
import tools.IntegrationTest
import tools.data.service.TestApiService

class MoviesListPmTest : IntegrationTest() {

    private val sut: MoviesListPm
        get() = deps.moviesListPm

    private val apiService: TestApiService
        get() = deps.apiService

    private lateinit var itemsObserver: TestObserver<List<MovieTileLayout>>

    private lateinit var loadingObserver: TestObserver<Boolean>

    private lateinit var pmTestHelper: PmTestHelper

    override fun beforeLaunch() {
        super.beforeLaunch()
        pmTestHelper = PmTestHelper(sut)
        itemsObserver = sut.items.observable.test()
        loadingObserver = sut.loading.observable.test()
    }

    @Test
    fun testInitialState() {
        pmTestHelper.setLifecycleTo(PresentationModel.Lifecycle.CREATED)
        itemsObserver.assertValueCount(1)
        itemsObserver.assertValueAt(0) { it.size == 10 }
        itemsObserver.assertValueAt(0) {
            it.all { item -> item is MovieTileLayout.Loading }
        }
        itemsObserver.assertNotComplete()

        loadingObserver.assertValueCount(1)
        loadingObserver.assertValueAt(0, true)
        loadingObserver.assertNotComplete()
    }

    @Test
    fun testItems() {
        pmTestHelper.setLifecycleTo(PresentationModel.Lifecycle.CREATED)
        apiService.movies.accept(movieList)
        itemsObserver.assertValueCount(2)
        itemsObserver.assertValueAt(1) { it.size == 1 }
        itemsObserver.assertValueAt(1) { it[0] is MovieTileLayout.Item }
        itemsObserver.assertValueAt(1) { (it[0] as MovieTileLayout.Item).viewModel.movie == movieList[0] }
        itemsObserver.assertNotComplete()

        loadingObserver.assertValueCount(2)
        loadingObserver.assertValueAt(1, false)
        loadingObserver.assertNotComplete()
    }

    @Test
    fun testError() {
        pmTestHelper.setLifecycleTo(PresentationModel.Lifecycle.CREATED)
        apiService.movies.error(Throwable(""))
        itemsObserver.assertValueCount(1)
        itemsObserver.assertNotComplete()

        loadingObserver.assertValueCount(2)
        loadingObserver.assertValueAt(1, false)
        loadingObserver.assertNotComplete()
    }

    private val movieList = listOf(Movie(123L, "Name", "PosterUrl", 1L))
}