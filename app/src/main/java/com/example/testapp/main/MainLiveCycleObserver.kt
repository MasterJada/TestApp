package com.example.testapp.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.testapp.repo.Repository

class MainLiveCycleObserver: LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun applicationStarted(){
        Repository.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun applicationStoped(){
        Repository.stop()
    }
}