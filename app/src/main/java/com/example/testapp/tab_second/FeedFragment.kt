package com.example.testapp.tab_second


import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testapp.*

import com.example.testapp.adapters.RssAdapter
import com.example.testapp.models.Article
import com.example.testapp.tab_one.FirstTabViewModel
import com.example.testapp.web_view_activiy.EXTRA_URL
import com.example.testapp.web_view_activiy.WebActivity
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!)[FeedViewModel::class.java]
        firstTabVM = ViewModelProviders.of(activity!!)[FirstTabViewModel::class.java]
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    lateinit var viewModel: FeedViewModel
    lateinit var firstTabVM: FirstTabViewModel

    private val businessAdapter = RssAdapter()
    private val otherNewsAdapter = RssAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        businessAdapter.setOnClick(::onClick)
        otherNewsAdapter.setOnClick(::onClick)

        rv_feed1.layoutManager = LinearLayoutManager(context)
        rv_feed1.adapter = businessAdapter

        tabs.addOnTabSelectedListener(createTabListener {
            rv_feed1.adapter = if (it.position == 0) {
                businessAdapter
            } else {
                otherNewsAdapter
            }
        })

        viewModel.businessArticles.observe(viewLifecycleOwner) {
            businessAdapter.items = it
        }
        viewModel.otherArticles.observe(viewLifecycleOwner){
            otherNewsAdapter.items = it
        }
    }

    private fun onClick(article: Article) {
        firstTabVM.selectedItem.postValue(article)

        Intent(activity, WebActivity::class.java).apply {
            putExtra(EXTRA_URL, article.link)
            if (this.resolveActivity(activity?.packageManager ?: return) != null) {
                startActivity(this)
            }
        }
    }

}
