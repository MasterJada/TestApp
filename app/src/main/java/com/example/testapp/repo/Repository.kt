package com.example.testapp.repo

import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.concurrent.timerTask
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.example.testapp.App
import com.example.testapp.plusAssign
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


object Repository {
    private val compositeDisposable = CompositeDisposable()

    fun start() {


        compositeDisposable += Observable.interval(1, TimeUnit.SECONDS)
            .subscribe {
                mutableTime.postValue(Date().time)
            }

        compositeDisposable += Observable.interval(5, TimeUnit.SECONDS)
            .subscribe {
                loadDataFromWeb()
            }

        compositeDisposable += Observable.interval(30, TimeUnit.SECONDS)
            .subscribe {
                isConnected()
            }
    }

    fun stop() {

        compositeDisposable.clear()
    }

    private fun loadDataFromWeb() {
        businessItems.load()
        otherNews.load()
    }

    val mutableTime = MutableLiveData<Long>()
    val isLoading = MutableLiveData<Boolean>()

    val businessItems = BussinessLiveData()
    val otherNews = OtherLiveData()

    val networkState = MutableLiveData<Boolean>()


    private fun isConnected() {
        val connectivityManager = App.instance.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        networkState.postValue(networkInfo != null && networkInfo.isConnected)
    }
}