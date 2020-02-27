package com.movies.data.command

import com.movies.api.Movie
import com.movies.data.service.ApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetMoviesListCommand @Inject constructor(
    private val apiService: ApiService
) {

    fun execute(): Single<List<Movie>> {
        return apiService.getMovies()
            .subscribeOn(Schedulers.io())
    }
}