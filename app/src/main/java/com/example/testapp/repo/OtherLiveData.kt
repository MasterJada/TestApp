package com.example.testapp.repo

import androidx.lifecycle.LiveData
import com.example.testapp.models.Article
import com.example.testapp.models.RSSFeed
import com.example.testapp.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OtherLiveData: LiveData<List<Article>>() {
    private val client = Api.getClient()
    private val compositeDisposable = CompositeDisposable()

    fun load(){
        compositeDisposable += client.getEntertainment()
            .concatWith(client.getEnvironment())
            .distinctUntilChanged { t1: RSSFeed, t2: RSSFeed ->
                t1.articleList.containsAll(t2.articleList)
            }
            .buffer(2)
            .map { it[0].articleList + it[1].articleList }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
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