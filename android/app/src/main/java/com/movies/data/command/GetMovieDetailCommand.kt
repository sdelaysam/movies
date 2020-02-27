package com.movies.data.command

import com.movies.api.MovieDetail
import com.movies.data.service.ApiService
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetMovieDetailCommand @Inject constructor(
    private val apiService: ApiService
) {

    fun execute(movieId: Long): Maybe<MovieDetail> {
        return apiService.getMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
    }
}