package com.movies.ui.moviesList

import com.movies.api.Movie
import com.movies.data.command.GetMoviesListCommand
import com.movies.navigation.Navigation
import com.movies.ui.moviesList.itemModels.MovieTileViewModel
import com.movies.util.rx.ignoreErrors
import me.dmdev.rxpm.*
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationalPm
import javax.inject.Inject

class MoviesListPm @Inject constructor(
    private val getMoviesListCommand: GetMoviesListCommand
) : PresentationModel(), NavigationalPm {

    val loading = state(true)

    val items = state(getLoadingTiles())

    private val itemClickAction = action<Movie>()

    override val navigationMessages = command<NavigationMessage>()

    override fun onCreate() {
        getMoviesListCommand.execute()
            .bindProgress(loading)
            .map(::getMovieTiles)
            .ignoreErrors() // API doesn't throw errors in this sample
            .subscribe(items)
            .untilDestroy()

        itemClickAction.observable
            .map { Navigation.MovieDetail(it) }
            .subscribe { navigationMessages.accept(it) }
            .untilDestroy()
    }

    private fun getMovieTiles(movies: List<Movie>): List<MovieTileLayout> {
        return movies
            .map { MovieTileLayout.Item(MovieTileViewModel(it, itemClickAction)) }
    }

    private fun getLoadingTiles(): List<MovieTileLayout> {
        return List(10) { MovieTileLayout.Loading }
    }
}