package com.example.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.adapters.MainFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = MainFragmentAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(pager, true)
    }
}
