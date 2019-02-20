package com.example.testapp

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject


object RxBus {
    private var publisher: BehaviorSubject<Any> = BehaviorSubject.create()
    fun publish(event: Any){
        publisher.onNext(event)
    }
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}