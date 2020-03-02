package com.movies.navigation

import com.movies.api.Movie
import me.dmdev.rxpm.navigation.NavigationMessage

sealed class Navigation : NavigationMessage {
    object Back : Navigation()
    object MoviesList : Navigation()
    class MovieDetail(val movie: Movie) : Navigation()
}