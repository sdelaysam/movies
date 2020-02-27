package com.movies.ui.moviesList

import com.movies.R
import com.movies.util.rxpm.BaseController
import kotlinx.android.synthetic.main.layout_movies_list.*
import me.dmdev.rxpm.bindTo
import com.jakewharton.rxbinding3.view.visibility

class MoviesListController : BaseController<MoviesListPm>(MoviesListPm::class, R.layout.layout_movies_list) {

    override fun onBindPresentationModel(pm: MoviesListPm) {
        pm.loading bindTo progress_bar.visibility()
    }
}