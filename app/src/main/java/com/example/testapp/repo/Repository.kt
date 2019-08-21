package com.example.testapp.repo

import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.concurrent.timerTask
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.example.testapp.App


object Repository {
    private  var timer : Timer? = null
    private var seconds = 0

    fun start() {
        timer = Timer()
        timer?.schedule(timerTask {
            mutableTime.postValue(Date().time)
            if(seconds % 5 == 0){
                loadDataFromWeb()
            }

            if(seconds % 30 == 0){
                isConnected()
            }
            seconds++
        }, 0, 1000)
    }

    fun stop() {
        timer?.cancel()
        timer = null
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