package com.movies.util.rx

import io.reactivex.Observable

fun <T> Observable<T>.ignoreErrors(): Observable<T> {
    return onErrorResumeNext(Observable.empty())
}