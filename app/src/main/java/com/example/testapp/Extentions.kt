package com.example.testapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


operator fun CompositeDisposable.plusAssign(disposable: Disposable){
    this.add(disposable)
}

fun<T> LiveData<T>.observe(owner: LifecycleOwner, callback: (T) -> Unit){
        this.observe(owner, Observer{ callback(it) })
}