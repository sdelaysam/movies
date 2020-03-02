package com.movies.ui.movieDetail.itemModels

import android.view.LayoutInflater
import android.view.View
import com.movies.R
import com.movies.api.Actor
import com.movies.util.view.DisposableViewBinder
import com.movies.util.rx.addTo
import com.movies.util.rx.ignoreErrors
import com.movies.util.view.ImageRequest
import com.movies.util.view.ViewBindings.bindImageUrl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.item_movie_actor.view.*
import kotlinx.android.synthetic.main.item_movie_actors.view.*

data class MovieActorsViewModel(val actors: List<Actor>): DisposableViewBinder {

    override fun bind(view: View): Disposable {
        val disposable = CompositeDisposable()
        val inflater = LayoutInflater.from(view.context)
        actors.forEach {
            val actorView = inflater.inflate(R.layout.item_movie_actor, view.actors_layout, false)
            actorView.title_text_view.text = it.name
            bindImageUrl(actorView.image_view, ImageRequest(url = it.imageUrl))
                .ignoreErrors()
                .subscribe()
                .addTo(disposable)
            view.actors_layout.addView(actorView)
        }
        return disposable
    }

    val hashCode: Int by lazy {
        actors.hashCode()
    }
}