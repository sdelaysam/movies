package com.movies.util.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movies.util.HasViewModel
import com.movies.util.Identifiable
import com.movies.util.IdentifiableLayout
import com.movies.util.view.DisposableViewBinder
import com.movies.util.view.ViewBinder

open class DiffAdapter<L: IdentifiableLayout>(private var items: List<L>? = null) : RecyclerView.Adapter<ViewHolder>() {

    private val helper by lazy { AsyncListDiffer<L>(this, createDiffCallback()) }

    fun update(items: List<L>) {
        if (this.items != items) {
            this.items = items
            helper.submitList(items)
        }
    }

    override fun getItemCount(): Int {
        return helper.currentList.size
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    protected fun getItemAt(position: Int): L? {
        return helper.currentList.getOrNull(position)
    }

    companion object {
        private fun <L: Identifiable> createDiffCallback() = object: DiffUtil.ItemCallback<L>() {
            override fun areItemsTheSame(oldItem: L, newItem: L) = oldItem.identity == newItem.identity
            override fun areContentsTheSame(oldItem: L, newItem: L) = oldItem.hashCode == newItem.hashCode
        }
    }
}