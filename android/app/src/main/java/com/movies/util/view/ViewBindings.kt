package com.movies.util.view

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DimenRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

object ViewBindings {

    fun bindImageUrl(imageView: ImageView, imageRequest: ImageRequest?): Single<Unit> {
        return Single
            .create<Unit> { observer ->
                Picasso.get()
                    .request(imageRequest, imageView.context)
                    .centerCrop()
                    .into(imageView, object : Callback {
                        override fun onSuccess() {
                            observer.onSuccess(Unit)
                        }

                        override fun onError(e: Exception?) {
                            observer.tryOnError(e!!)
                        }
                    })
                if (imageRequest?.url.isNullOrEmpty()) {
                    observer.onSuccess(Unit)
                }
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnDispose { Picasso.get().cancelRequest(imageView) }

    }
}

fun Picasso.request(request: ImageRequest?, context: Context): RequestCreator {
    return load(request?.url?.takeIf { it.isNotEmpty() })
        .apply {
            if (request?.width != null && request.height != null) {
                resize(
                    context.resources.getDimensionPixelSize(request.width),
                    context.resources.getDimensionPixelSize(request.height)
                )
            } else {
                fit()
            }
        }
}

data class ImageRequest(
    val url: String,
    @DimenRes val width: Int? = null,
    @DimenRes val height: Int? = null
)