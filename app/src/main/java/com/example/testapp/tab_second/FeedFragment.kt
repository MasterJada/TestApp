package com.example.testapp.tab_second


import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import com.example.testapp.Api
import com.example.testapp.LoadingEnum

import com.example.testapp.R
import com.example.testapp.RxBus
import com.example.testapp.adapters.RssAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private val disposable = CompositeDisposable()
    private var businessDisposable: Disposable? = null
    private var newsDisposable: Disposable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    private val handler = Handler()
    private val businessAdapter = RssAdapter()
    private val newsAdapter = RssAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.post(runnable)
        rv_feed1.layoutManager = LinearLayoutManager(context)
        rv_feed1.adapter = businessAdapter
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                rv_feed1.adapter =  if(p0?.position == 0) businessAdapter else newsAdapter
            }

        })
    }


    private fun updateInfo() {
        val client = Api.getClient()
        businessDisposable = client.getBusinessNews()
            .map { it.articleList }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handler.post {
                    businessAdapter.items = it
                }
            }, {
                it.printStackTrace()

            }, {
                businessDisposable?.dispose()
                RxBus.publish(LoadingEnum.STOP_LOAD)
            }, {
                RxBus.publish(LoadingEnum.LOADING)
            })

        newsDisposable =
            client.getEntertainment()
                .concatWith(client.getEnvironment())
                .map { it.articleList }
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    handler.post {
                       newsAdapter.items = it
                    }
                }, {
                    it.printStackTrace()
                }, {
                    businessDisposable?.dispose()
                    RxBus.publish(LoadingEnum.STOP_LOAD)
                }, {
                    RxBus.publish(LoadingEnum.LOADING)
                })

        handler.postDelayed(runnable, 5000)
    }


    private var runnable = {
        updateInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}
