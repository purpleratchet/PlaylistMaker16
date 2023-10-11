package com.smsman.playlistmaker.search.domain

interface HistoryRepository {
    fun addTrackToHistory(track: ItunesSearchResult)
    fun clearSearchHistory()
    fun saveSearchHistory()
    fun loadSearchHistory(): MutableList<ItunesSearchResult>

}