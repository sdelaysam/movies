package com.movies.navigation

import me.dmdev.rxpm.navigation.NavigationMessage

sealed class Navigation : NavigationMessage {
    object Back : Navigation()
    object MoviesList : Navigation()
    class MovieDetail(val id: Long) : Navigation()
}