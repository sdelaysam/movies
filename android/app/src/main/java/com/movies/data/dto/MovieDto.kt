package com.movies.data.dto

import android.os.Parcelable
import com.movies.api.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDto(
    val id: Long,
    val name: String,
    val posterUrl: String
): Parcelable

val Movie.dto: MovieDto
    get() = MovieDto(id = id, name = name, posterUrl = posterUrl)