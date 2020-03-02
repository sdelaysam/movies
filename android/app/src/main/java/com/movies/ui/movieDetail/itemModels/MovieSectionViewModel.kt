package com.movies.ui.movieDetail.itemModels

import android.view.View
import com.movies.util.view.ViewBinder
import kotlinx.android.synthetic.main.item_movie_section.view.*

data class MovieSectionViewModel(val name: String): ViewBinder {

    override fun bind(view: View) {
        view.title_text_view.text = name
    }
}