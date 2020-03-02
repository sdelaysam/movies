package com.movies.util.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.movies.util.view.DisposableViewBinder
import com.movies.util.view.ViewBinder
import io.reactivex.disposables.Disposable

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private var disposable: Disposable? = null

    fun bind(binder: ViewBinder) {
        binder.bind(view)
    }

    fun bind(binder: DisposableViewBinder) {
        disposable?.dispose()
        disposable = binder.bind(view)
    }

    fun clear() {
        disposable?.dispose()
    }
}