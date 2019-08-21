package com.example.testapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, callback: (T) -> Unit) {
    this.observe(owner, Observer { callback(it) })
}


fun createTabListener(listener: (TabLayout.Tab) -> Unit): TabLayout.OnTabSelectedListener {
    return object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(p0: TabLayout.Tab?) {
            p0?.let(listener)
        }

        override fun onTabReselected(p0: TabLayout.Tab?) = Unit

        override fun onTabUnselected(p0: TabLayout.Tab?) = Unit
    }
}


