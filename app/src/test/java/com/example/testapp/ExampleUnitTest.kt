package com.example.testapp

import com.example.testapp.models.RSSFeed
import com.example.testapp.repo.Api
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun enviromentNewsLoading() {
        Api.getClient().getEnvironment().subscribe {
            assert(it.articleList.isNotEmpty())
        }
    }

    @Test
    fun bussinesNewsLoading(){
        Api.getClient().getBusinessNews().subscribe {
            assert(it.articleList.isNotEmpty())
        }
    }
    @Test
    fun entertaimentNewsLoading(){
        Api.getClient().getEntertainment().subscribe {
            assert(it.articleList.isNotEmpty())
        }
    }

    @Test
    fun summaryLoading(){
        Api.getClient().getEntertainment()
            .concatWith(Api.getClient().getEnvironment())
            .distinctUntilChanged { t1: RSSFeed, t2: RSSFeed ->
                t1.articleList.containsAll(t2.articleList)
            }
            .buffer(2)
            .map { it[0].articleList + it[1].articleList }
            .subscribe({
               assert(it.isNotEmpty())
            }, {
               throw it
            })
    }

}
