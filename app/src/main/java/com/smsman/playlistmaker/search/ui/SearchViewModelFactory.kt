package com.smsman.playlistmaker.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smsman.playlistmaker.search.domain.HistoryUseCase
import com.smsman.playlistmaker.search.domain.SearchUseCase

class SearchViewModelFactory(
    private val historyUseCase: HistoryUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(historyUseCase, searchUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}