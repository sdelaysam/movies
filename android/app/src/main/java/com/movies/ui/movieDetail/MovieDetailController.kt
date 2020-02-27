package com.movies.ui.movieDetail

import android.os.Bundle
import com.movies.R
import com.movies.util.rxpm.BaseController

class MovieDetailController(bundle: Bundle) : BaseController<MovieDetailPm>(MovieDetailPm::class, R.layout.layout_movie_detail, bundle) {

    private val movieId: Long by lazy {
        args.getLong(KEY_MOVIE_ID)
    }

    override fun onBindPresentationModel(pm: MovieDetailPm) {
    }

    companion object {
        private const val KEY_MOVIE_ID = "KEY_MOVIE_ID"

        fun newInstance(movieId: Long): MovieDetailController {
            return MovieDetailController(Bundle().apply {
                putLong(KEY_MOVIE_ID, movieId)
            })
        }
    }
}