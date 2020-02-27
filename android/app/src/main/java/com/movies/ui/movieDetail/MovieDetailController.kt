package com.movies.ui.movieDetail

import android.os.Bundle
import com.jakewharton.rxbinding3.view.visibility
import com.movies.R
import com.movies.util.rxpm.BaseController
import kotlinx.android.synthetic.main.layout_movie_detail.*
import me.dmdev.rxpm.bindTo
import me.dmdev.rxpm.passTo

class MovieDetailController(bundle: Bundle) : BaseController<MovieDetailPm>(MovieDetailPm::class, R.layout.layout_movie_detail, bundle) {

    override fun onBindPresentationModel(pm: MovieDetailPm) {
        args.getLong(KEY_MOVIE_ID) passTo pm.movieId
        pm.loading bindTo progress_bar.visibility()
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