package com.movies.ui.movieDetail

import com.movies.R
import com.movies.api.Actor
import com.movies.api.MovieDetail
import com.movies.data.dto.MovieDto
import com.movies.ui.movieDetail.itemModels.MovieActorsViewModel
import com.movies.ui.movieDetail.itemModels.MovieHeaderViewModel
import com.movies.ui.movieDetail.itemModels.MovieSectionViewModel
import io.reactivex.observers.TestObserver
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.test.PmTestHelper
import org.junit.Assert.*
import org.junit.Test
import tools.IntegrationTest
import tools.data.service.TestApiService

class MovieDetailPmTest : IntegrationTest() {

    private val sut: MovieDetailPm
        get() = deps.movieDetailPm

    private val apiService: TestApiService
        get() = deps.apiService

    private lateinit var itemsObserver: TestObserver<List<MovieDetailLayout>>

    private lateinit var pmTestHelper: PmTestHelper

    override fun beforeLaunch() {
        super.beforeLaunch()
        pmTestHelper = PmTestHelper(sut)
        itemsObserver = sut.items.observable.test()
    }

    @Test
    fun testInitialState() {
        pmTestHelper.setLifecycleTo(PresentationModel.Lifecycle.CREATED)
        sut.movie.consumer.accept(movie)
        itemsObserver.assertValueCount(1)
        itemsObserver.assertValueAt(0) { it.size == 1 }
        itemsObserver.assertValueAt(0) { it[0] is MovieDetailLayout.Header }
        itemsObserver.assertValueAt(0) { verifyLoadingHeader((it[0] as MovieDetailLayout.Header).viewModel) }
        itemsObserver.assertNotComplete()
    }

    @Test
    fun testDetails() {
        pmTestHelper.setLifecycleTo(PresentationModel.Lifecycle.CREATED)
        sut.movie.consumer.accept(movie)
        apiService.movieDetail.accept(detail)
        itemsObserver.assertValueCount(2)
        itemsObserver.assertValueAt(1) { it.size == 3 }
        itemsObserver.assertValueAt(1) { it[0] is MovieDetailLayout.Header }
        itemsObserver.assertValueAt(1) { verifyHeader((it[0] as MovieDetailLayout.Header).viewModel) }
        itemsObserver.assertValueAt(1) { it[1] is MovieDetailLayout.Section }
        itemsObserver.assertValueAt(1) { verifySection((it[1] as MovieDetailLayout.Section).viewModel) }
        itemsObserver.assertValueAt(1) { it[2] is MovieDetailLayout.Actors }
        itemsObserver.assertValueAt(1) { verifyActors((it[2] as MovieDetailLayout.Actors).viewModel) }
        itemsObserver.assertNotComplete()
    }

    @Test
    fun testError() {
        pmTestHelper.setLifecycleTo(PresentationModel.Lifecycle.CREATED)
        sut.movie.consumer.accept(movie)
        apiService.movieDetail.error(Throwable(""))
        itemsObserver.assertValueCount(1)
        itemsObserver.assertNotComplete()
    }

    private fun verifyLoadingHeader(viewModel: MovieHeaderViewModel): Boolean {
        assertTrue(viewModel.loading)
        assertEquals(movie.name, viewModel.name)
        assertEquals(movie.posterUrl, viewModel.posterUrl)
        assertNull(viewModel.description)
        assertNull(viewModel.rating)
        return true
    }

    private fun verifyHeader(viewModel: MovieHeaderViewModel): Boolean {
        assertFalse(viewModel.loading)
        assertEquals(detail.name, viewModel.name)
        assertEquals(detail.posterUrl, viewModel.posterUrl)
        assertEquals(detail.description, viewModel.description)
        assertEquals(detail.score, viewModel.rating)
        return true
    }

    private fun verifySection(viewModel: MovieSectionViewModel): Boolean {
        assertEquals(context.getString(R.string.actors), viewModel.name)
        return true
    }

    private fun verifyActors(viewModel: MovieActorsViewModel): Boolean {
        assertEquals(actors, viewModel.actors)
        return true
    }

    private val movie = MovieDto(id = 123L, name = "Name", posterUrl = "PosterURL")

    private val actors = arrayListOf(Actor("ActorName", 0, "ImageURL"))

    private val detail = MovieDetail(movie.id, "DetailName", "Description", "DetailDescription", 10f, actors)
}