package com.movies.ui.movieDetail.itemModels

import android.view.View
import androidx.core.view.ViewCompat
import com.movies.R
import com.movies.util.rx.ignoreErrors
import com.movies.util.view.DisposableViewBinder
import com.movies.util.view.ImageRequest
import com.movies.util.view.ViewBindings.bindImageUrl
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.item_movie_header.view.*

data class MovieHeaderViewModel(
    val id: Long,
    val name: String,
    val posterUrl: String,
    val description: String?,
    val rating: Float?,
    val loading: Boolean
) : DisposableViewBinder {

    override fun bind(view: View): Disposable {
        ViewCompat.setTransitionName(view.image_view, view.context.getString(R.string.movie_image, id))
        ViewCompat.setTransitionName(view.title_text_view, view.context.getString(R.string.movie_title, id))

        if (loading) {
            view.description_text_view.visibility = View.GONE
            view.rating_bar.visibility = View.GONE
            view.rating_label.visibility = View.GONE
            view.rating_value.visibility = View.GONE
            view.shimmer.visibility = View.VISIBLE
            view.shimmer.startShimmer()
        } else {
            view.description_text_view.text = description
            view.description_text_view.visibility = View.VISIBLE
            view.rating_bar.rating = (rating ?: 0f) / 10
            view.rating_bar.visibility = View.VISIBLE
            view.rating_label.visibility = View.VISIBLE
            view.rating_value.text = view.context.getString(R.string.rating_text, rating?.toInt() ?: 0)
            view.rating_value.visibility = View.VISIBLE
            view.shimmer.visibility = View.GONE
            view.shimmer.setShimmer(null)
        }
        view.title_text_view.text = name
        val request = ImageRequest(url = posterUrl, width = R.dimen.tile_width, height = R.dimen.tile_height)
        return bindImageUrl(view.image_view, request)
            .ignoreErrors()
            .subscribe()
    }

    val hashCode: Int by lazy {
        var result = name.hashCode()
        result = 31 * result + posterUrl.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + loading.hashCode()
        result
    }
}