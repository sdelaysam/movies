package com.movies.ui.movieDetail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.movies.R
import com.movies.api.Movie
import com.movies.data.dto.MovieDto
import com.movies.data.dto.dto
import com.movies.util.recyclerView.DiffAdapter
import com.movies.util.rxpm.BaseController
import kotlinx.android.synthetic.main.layout_movie_detail.*
import me.dmdev.rxpm.bindTo
import me.dmdev.rxpm.passTo

class MovieDetailController(bundle: Bundle) : BaseController<MovieDetailPm>(MovieDetailPm::class, R.layout.layout_movie_detail, bundle) {

    private val adapter = DiffAdapter<MovieDetailLayout>()

    override fun onViewCreated(view: View) {
        val context = view.context
        (recycler_view.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
    }

    override fun onDestroyView(view: View) {
        recycler_view.adapter = null
        super.onDestroyView(view)
    }

    override fun onBindPresentationModel(pm: MovieDetailPm) {
        args.getParcelable<MovieDto>(KEY_MOVIE)!! passTo pm.movie
        pm.items bindTo { adapter.update(it) }
    }

    companion object {
        private const val KEY_MOVIE = "KEY_MOVIE"

        fun newInstance(movie: Movie): MovieDetailController {
            return MovieDetailController(Bundle().apply {
                putParcelable(KEY_MOVIE, movie.dto)
            })
        }
    }
}