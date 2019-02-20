package com.example.testapp

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface Api {
    @GET("businessnews?format=json")
   // @GET("article.rss")
    fun getBusinessNews(): Observable<RSSFeed>
    @GET("entertainment")
    fun getEntertainment(): Observable<RSSFeed>
    @GET("environment")
    fun getEnvironment(): Observable<RSSFeed>

    companion object {
        fun getClient() : Api {
            val builder = Retrofit.Builder()
                .baseUrl("http://feeds.reuters.com/reuters/")
               // .baseUrl("http://vogella.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
            return builder.build().create()
        }
    }
}