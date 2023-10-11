package com.smsman.playlistmaker.search.domain

interface SearchUseCase {
    fun search(
        query: String,
        onResponse: (List<ItunesSearchResult>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}