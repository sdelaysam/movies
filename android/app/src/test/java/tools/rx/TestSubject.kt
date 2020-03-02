package tools.rx

import io.reactivex.*
import io.reactivex.subjects.BehaviorSubject

class TestSubject<T> {

    private var subject = BehaviorSubject.create<Notification<T>>()

    fun accept(value: T) {
        subject.onNext(Notification.createOnNext(value))
    }

    fun error(throwable: Throwable) {
        subject.onNext(Notification.createOnError(throwable))
    }

    fun complete() {
        subject.onNext(Notification.createOnComplete())
    }

    fun reset() {
        subject.onComplete()
        subject = BehaviorSubject.create<Notification<T>>()
    }

    fun toObservable(): Observable<T> {
        return subject.dematerialize<T>()
    }

    fun toSingle(): Single<T> {
        return toObservable().take(1).singleOrError()
    }

    fun toMaybe(): Maybe<T> {
        return toObservable().take(1).singleElement()
    }

    fun toCompletable(): Completable {
        return toObservable().ignoreElements()
    }
}