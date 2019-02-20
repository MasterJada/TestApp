package com.example.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.testapp.adapters.MainFragmentAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val disposables = CompositeDisposable()
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = MainFragmentAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(pager, true)
        disposables.add( RxBus.listen(LoadingEnum::class.java).subscribe {
           handler.post {
               loading_bar.visibility = if(it == LoadingEnum.LOADING) View.VISIBLE else View.GONE
           }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
