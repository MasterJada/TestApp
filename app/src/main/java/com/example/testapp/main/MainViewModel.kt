package com.example.testapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.testapp.repo.Repository

class MainViewModel: ViewModel() {
    val isLoading: LiveData<Boolean> = Transformations.map(Repository.isLoading){ it }
}