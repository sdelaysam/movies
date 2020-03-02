package com.movies.util.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movies.util.HasViewModel
import com.movies.util.Layout
import com.movies.util.view.DisposableViewBinder
import com.movies.util.view.ViewBinder

open class SimpleAdapter<L : Layout>(private var items: List<L>? = null) : RecyclerView.Adapter<ViewHolder>() {

    fun getLayoutAt(position: Int): L? {
        return getItemAt(position)
    }

    protected fun getItemAt(position: Int): L? {
        return items?.getOrNull(position)
    }

    open fun update(items: List<L>) {
        if (this.items != items) {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItemAt(position)?.layoutId ?: -1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = getItemAt(position)) {
            is ViewBinder -> holder.bind(item)
            is DisposableViewBinder -> holder.bind(item)
            else -> {
                item?.let { it as? HasViewModel }
                    ?.viewModel
                    ?.let {
                        when (it) {
                            is ViewBinder -> holder.bind(it)
                            is DisposableViewBinder -> holder.bind(it)
                            else -> throw UnsupportedOperationException("Can't bind view model: $it")
                        }
                    }
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }
}