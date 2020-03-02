package com.movies.util.rx

import io.reactivex.Maybe
import io.reactivex.Single

fun <T> Single<T>.ignoreErrors(): Maybe<T> {
    return toMaybe().onErrorResumeNext(Maybe.empty())
}