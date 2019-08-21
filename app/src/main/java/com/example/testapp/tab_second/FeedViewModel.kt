package com.example.testapp.tab_second


import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.testapp.models.Article
import com.example.testapp.repo.Repository


class FeedViewModel: ViewModel() {
    val businessArticles: LiveData<List<Article>> by lazy {   Repository.businessItems }
    val otherArticles: LiveData<List<Article>> by lazy {   Repository.otherNews }
}