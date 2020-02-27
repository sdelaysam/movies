package com.movies.ui.moviesList

import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.test.PmTestHelper
import org.junit.Test
import tools.IntegrationTest
import tools.data.service.TestApiService

class MoviesListPmTest : IntegrationTest() {

    private val apiService: TestApiService
        get() = deps.apiService

    private val sut: MoviesListPm
        get() = deps.moviesListPm

    private lateinit var helper: PmTestHelper

    override fun beforeLaunch() {
        super.beforeLaunch()
        helper = PmTestHelper(sut)
    }

    @Test
    fun test() {
        helper.setLifecycleTo(PresentationModel.Lifecycle.RESUMED)
    }

}