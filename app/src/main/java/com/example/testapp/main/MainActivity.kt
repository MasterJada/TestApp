package com.example.testapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testapp.R
import com.example.testapp.adapters.MainFragmentAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager.adapter = MainFragmentAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(pager, true)
        lifecycle.addObserver(MainLiveCycleObserver())
    }

    override fun onStart() {
        super.onStart()
        ViewModelProviders.of(this)[MainViewModel::class.java].isLoading.observe(this, Observer {
            loading_bar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}
