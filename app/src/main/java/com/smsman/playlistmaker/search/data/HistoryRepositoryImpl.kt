package com.smsman.playlistmaker.search.data

import android.content.SharedPreferences
import com.smsman.playlistmaker.search.domain.HistoryRepository
import com.smsman.playlistmaker.search.domain.ItunesSearchResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoryRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    HistoryRepository {
    private var searchHistory: MutableList<ItunesSearchResult> = loadSearchHistory()

    companion object {
        const val PREFERENCES_KEY = "search_history"
    }

    override fun addTrackToHistory(track: ItunesSearchResult) {
        searchHistory.removeAll { it.trackId == track.trackId }
        searchHistory.add(0, track)
        if (searchHistory.size > 10) {
            searchHistory.removeLast()
        }
        saveSearchHistory()
    }

    override fun clearSearchHistory() {
        sharedPreferences.edit().remove(PREFERENCES_KEY).apply()
    }

    override fun saveSearchHistory() {
        val editor = sharedPreferences.edit()
        val searchHistoryJson = Gson().toJson(searchHistory)
        editor.putString(PREFERENCES_KEY, searchHistoryJson)
        editor.apply()
    }

    override fun loadSearchHistory(): MutableList<ItunesSearchResult> {
        val searchHistoryJson = sharedPreferences.getString(PREFERENCES_KEY, null)
        val type = object : TypeToken<MutableList<ItunesSearchResult>>() {}.type
        return Gson().fromJson(searchHistoryJson, type) ?: mutableListOf()
    }
}