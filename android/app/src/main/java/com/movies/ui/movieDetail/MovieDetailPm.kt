package com.movies.ui.movieDetail

import com.movies.data.command.GetMovieDetailCommand
import me.dmdev.rxpm.*
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationalPm
import timber.log.Timber
import javax.inject.Inject

class MovieDetailPm @Inject constructor(
    private val getMovieDetailCommand: GetMovieDetailCommand
) : PresentationModel(), NavigationalPm {

    val loading = state(false)

    val movieId = action<Long>()

    override val navigationMessages = command<NavigationMessage>()

    override fun onCreate() {
        movieId.observable
            .distinctUntilChanged()
            .switchMapMaybe {
                getMovieDetailCommand.execute(it)
                    .bindProgress(loading)
            }
            .subscribe { Timber.d("MovieDetail: $it") }
            .untilDestroy()

    }
}