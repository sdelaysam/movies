package com.movies.ui.movieDetail

import com.movies.data.command.GetMovieDetailCommand
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.command
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationalPm
import javax.inject.Inject

class MovieDetailPm @Inject constructor(
    private val getMovieDetailCommand: GetMovieDetailCommand
) : PresentationModel(), NavigationalPm {

    override val navigationMessages = command<NavigationMessage>()
}