package com.movies.ui.movieDetail

import android.content.Context
import com.movies.R
import com.movies.api.MovieDetail
import com.movies.data.command.GetMovieDetailCommand
import com.movies.data.dto.MovieDto
import com.movies.ui.movieDetail.itemModels.MovieActorsViewModel
import com.movies.ui.movieDetail.itemModels.MovieHeaderViewModel
import com.movies.ui.movieDetail.itemModels.MovieSectionViewModel
import com.movies.util.rx.ignoreErrors
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.action
import me.dmdev.rxpm.command
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationalPm
import me.dmdev.rxpm.state
import javax.inject.Inject

class MovieDetailPm @Inject constructor(
    private val context: Context,
    private val getMovieDetailCommand: GetMovieDetailCommand
) : PresentationModel(), NavigationalPm {

    val movie = action<MovieDto>()

    val items = state<List<MovieDetailLayout>>()

    override val navigationMessages = command<NavigationMessage>()

    override fun onCreate() {
        movie.observable
            .distinctUntilChanged()
            .switchMap { movie ->
                getMovieDetailCommand.execute(movie.id)
                    .toObservable()
                    .ignoreErrors()
                    .map(::getMovieDetailItems)
                    .startWith(getMovieItems(movie))
            }
            .subscribe(items)
            .untilDestroy()

    }

    private fun getMovieItems(movie: MovieDto): List<MovieDetailLayout> {
        return listOf(
            MovieDetailLayout.Header(MovieHeaderViewModel(id = movie.id,
                name = movie.name,
                posterUrl = movie.posterUrl,
                description = null,
                rating = null,
                loading = true))
        )
    }

    private fun getMovieDetailItems(detail: MovieDetail): List<MovieDetailLayout> {
        return listOf(
            MovieDetailLayout.Header(MovieHeaderViewModel(id = detail.id,
                name = detail.name,
                posterUrl = detail.posterUrl,
                description = detail.description,
                rating = detail.score,
                loading = false)),
            MovieDetailLayout.Section(MovieSectionViewModel(name = context.getString(R.string.actors))),
            MovieDetailLayout.Actors(MovieActorsViewModel(detail.actors)))
    }

}