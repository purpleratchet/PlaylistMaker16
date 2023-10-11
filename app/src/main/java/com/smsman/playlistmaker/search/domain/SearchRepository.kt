package com.smsman.playlistmaker.search.domain

interface SearchRepository {
    fun search(
        query: String,
        onResponse: (List<ItunesSearchResult>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}