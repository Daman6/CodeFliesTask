package com.example.codefliestask.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codefliestask.model.TherapiesModel
import com.example.codefliestask.repo.TherapyRepo

class TherapiesViewModelProviderFactory(val repo: TherapyRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}