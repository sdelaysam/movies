package com.movies.ui.moviesList.itemModels

import android.view.View
import androidx.core.view.ViewCompat
import com.jakewharton.rxbinding3.view.clicks
import com.movies.R
import com.movies.api.Movie
import com.movies.util.view.DisposableViewBinder
import com.movies.util.rx.addTo
import com.movies.util.rx.ignoreErrors
import com.movies.util.view.ImageRequest
import com.movies.util.view.ViewBindings.bindImageUrl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.item_movie_tile.view.*
import me.dmdev.rxpm.Action

data class MovieTileViewModel(
    val movie: Movie,
    val action: Action<Movie>
) : DisposableViewBinder {

    override fun bind(view: View): Disposable {
        ViewCompat.setTransitionName(view.image_view, view.context.getString(R.string.movie_image, movie.id))
        ViewCompat.setTransitionName(view.title_text_view, view.context.getString(R.string.movie_title, movie.id))
        val disposable = CompositeDisposable()
        view.title_text_view.text = movie.name
        view.clicks()
            .subscribe { action.consumer.accept(movie) }
            .addTo(disposable)
        val request = ImageRequest(url = movie.posterUrl, width = R.dimen.tile_width, height = R.dimen.tile_height)
        bindImageUrl(view.image_view, request)
            .ignoreErrors()
            .subscribe()
            .addTo(disposable)
        return disposable
    }
}