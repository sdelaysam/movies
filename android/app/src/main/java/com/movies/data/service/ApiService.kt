package com.movies.data.service

import com.movies.api.Api
import com.movies.api.Movie
import com.movies.api.MovieDetail
import io.reactivex.Maybe
import io.reactivex.Single

interface ApiService {
    fun getMovies(): Single<List<Movie>>
    fun getMovieDetail(id: Long): Maybe<MovieDetail>
}

class DefaultApiService constructor(private val api: Api) : ApiService {

    override fun getMovies(): Single<List<Movie>> {
        return Single.fromCallable { api.movies }
    }

    override fun getMovieDetail(id: Long): Maybe<MovieDetail> {
        return Maybe.create { observer ->
            val detail = api.getMovieDetail(id)
            if (detail != null) {
                observer.onSuccess(detail)
            } else {
                observer.onComplete()
            }
        }
    }
}