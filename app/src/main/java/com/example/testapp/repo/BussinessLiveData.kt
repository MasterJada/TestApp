package com.example.testapp.repo

import androidx.lifecycle.LiveData
import com.example.testapp.models.Article
import com.example.testapp.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BussinessLiveData: LiveData<List<Article>>() {
    private val client = Api.getClient()
    private val compositeDisposable = CompositeDisposable()

    fun load(){
        compositeDisposable += client.getBusinessNews()
            .map { it.articleList }
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { Repository.isLoading.postValue(true) }
            .doOnComplete {
                compositeDisposable.clear()
                Repository.isLoading.postValue(false)
            }
            .subscribe({
                postValue(it)
            }, {
                it.printStackTrace()
            })
    }


    override fun onInactive() {
        compositeDisposable.clear()
    }

}