package com.movies.ui.moviesList

import com.movies.data.command.GetMoviesListCommand
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.bindProgress
import me.dmdev.rxpm.command
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationalPm
import me.dmdev.rxpm.state
import javax.inject.Inject

class MoviesListPm @Inject constructor(
    private val getMoviesListCommand: GetMoviesListCommand
) : PresentationModel(), NavigationalPm {

    val loading = state(false)

    override val navigationMessages = command<NavigationMessage>()

    override fun onCreate() {
        getMoviesListCommand.execute()
            .bindProgress(loading)
            .subscribe()
            .untilDestroy()
    }
}