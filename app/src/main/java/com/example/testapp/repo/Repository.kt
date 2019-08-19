package com.example.testapp.repo

import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.concurrent.timerTask

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


}