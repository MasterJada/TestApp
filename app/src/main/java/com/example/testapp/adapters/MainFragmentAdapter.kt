package com.example.testapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.testapp.tab_one.BlankFragment
import com.example.testapp.tab_second.FeedFragment

class MainFragmentAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return  if(p0 == 0) BlankFragment.instance
        else FeedFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(position == 0) "Tab 0" else "Tab 1"
    }
    override fun getCount() = 2
}