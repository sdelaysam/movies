package com.movies.ui.moviesList

import com.movies.R
import com.movies.ui.moviesList.itemModels.MovieTileViewModel
import com.movies.util.HasViewModel
import com.movies.util.Layout

sealed class MovieTileLayout : Layout {

    class Item(override val viewModel: MovieTileViewModel) : MovieTileLayout(), HasViewModel
    object Loading : MovieTileLayout()

    override val layoutId: Int
        get() = when (this) {
            is Item -> R.layout.item_movie_tile
            is Loading -> R.layout.item_movie_tile_loading
        }
}