package com.example.testapp.tab_one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.testapp.models.Article
import com.example.testapp.repo.Repository
import java.text.SimpleDateFormat
import java.util.*

class FirstTabViewModel: ViewModel() {
    val selectedItem = MutableLiveData<Article>()

    val date: LiveData<String> = Transformations.map(Repository.mutableTime) {
        val pattern = SimpleDateFormat("hh:mm:ss dd.MM.yyyy", Locale.getDefault())
         pattern.format(Date(it))
    }

}