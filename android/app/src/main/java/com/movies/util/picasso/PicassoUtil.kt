package com.movies.util.picasso

import android.content.Context
import com.movies.BuildConfig
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.OkHttpClient
import timber.log.Timber


object PicassoUtil {

    fun initPicasso(context: Context) {
        val cache = Cache(context.cacheDir, 50 * 1024 * 1024) // 50MB
        val client = OkHttpClient.Builder()
            .followRedirects(true)
            .cache(cache)
            .build()
        val picasso = Picasso.Builder(context)
            .downloader(OkHttp3Downloader(client))
            .loggingEnabled(BuildConfig.DEBUG)
            .listener { _, uri, exception ->
                Timber.e(exception, uri.toString())
            }.build()
        Picasso.setSingletonInstance(picasso)
    }
}
