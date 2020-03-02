package com.movies.ui.moviesList

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.movies.R
import com.movies.util.recyclerView.GridSpacingItemDecoration
import com.movies.util.recyclerView.SimpleAdapter
import com.movies.util.rxpm.BaseController
import kotlinx.android.synthetic.main.layout_movies_list.*
import me.dmdev.rxpm.bindTo

class MoviesListController : BaseController<MoviesListPm>(MoviesListPm::class, R.layout.layout_movies_list) {

    private val adapter = SimpleAdapter<MovieTileLayout>()

    override fun onViewCreated(view: View) {
        val context = view.context
        recycler_view.addItemDecoration(GridSpacingItemDecoration(context.resources.getDimensionPixelSize(R.dimen.default_margin)))
        recycler_view.layoutManager = GridLayoutManager(context, context.resources.getInteger(R.integer.tiles_span_count))
        recycler_view.adapter = adapter
    }

    override fun onDestroyView(view: View) {
        recycler_view.adapter = null
        super.onDestroyView(view)
    }

    override fun onBindPresentationModel(pm: MoviesListPm) {
        pm.loading bindTo {
            if (it) {
                recycler_view.scrollToPosition(0)
                recycler_view.suppressLayout(true)
            } else {
                recycler_view.suppressLayout(false)
                shimmer.setShimmer(null)
            }
        }
        pm.items bindTo { adapter.update(it) }
    }
}