package com.example.testapp.tab_one


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_blank.*
import java.text.SimpleDateFormat
import java.util.*

class BlankFragment : Fragment() {

    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onResume() {
        super.onResume()
        handler.post(runnable)
    }

    private var runnable = {
        updateTime()
    }

    private fun updateTime() {
         val pattern = SimpleDateFormat("hh:mm:ss dd.MM.yyyy", Locale.getDefault())
        tv_date.text = pattern.format(Date())
         handler.postDelayed(runnable, 1000)
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }


    companion object {
        var instance  = BlankFragment()
    }
}
