package com.movies.ui.movieDetail

import com.movies.R
import com.movies.ui.movieDetail.itemModels.MovieActorsViewModel
import com.movies.ui.movieDetail.itemModels.MovieHeaderViewModel
import com.movies.ui.movieDetail.itemModels.MovieSectionViewModel
import com.movies.util.HasViewModel
import com.movies.util.IdentifiableLayout

sealed class MovieDetailLayout : IdentifiableLayout {

    class Header(override val viewModel: MovieHeaderViewModel): MovieDetailLayout(), HasViewModel
    class Section(override val viewModel: MovieSectionViewModel): MovieDetailLayout(), HasViewModel
    class Actors(override val viewModel: MovieActorsViewModel): MovieDetailLayout(), HasViewModel

    override val layoutId: Int
        get() = when (this) {
            is Header -> R.layout.item_movie_header
            is Section -> R.layout.item_movie_section
            is Actors -> R.layout.item_movie_actors
        }

    override val identity: Int
        get() = when (this) {
            is Header -> 0
            is Section -> 1
            is Actors -> 2
        }

    override val hashCode: Int
        get() = when (this) {
            is Header -> viewModel.hashCode
            is Section -> viewModel.name.hashCode()
            is Actors -> viewModel.hashCode
        }
}

