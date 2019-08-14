package com.example.testapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.testapp.tab_one.FirstTabFragment
import com.example.testapp.tab_second.FeedFragment

class MainFragmentAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return  if(p0 == 0) FirstTabFragment.instance
        else FeedFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(position == 0) "Tab 0" else "Tab 1"
    }
    override fun getCount() = 2
}