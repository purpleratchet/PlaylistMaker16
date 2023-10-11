package com.smsman.playlistmaker.creator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.smsman.playlistmaker.player.data.PlayerImpl
import com.smsman.playlistmaker.player.data.MediaDataSource
import com.smsman.playlistmaker.player.domain.api.PlayerInteractor
import com.smsman.playlistmaker.player.domain.api.MediaRepository
import com.smsman.playlistmaker.player.domain.impl.PlayerInteractorImpl
import com.smsman.playlistmaker.search.data.HistoryRepositoryImpl
import com.smsman.playlistmaker.search.data.ItunesSearchApi
import com.smsman.playlistmaker.search.data.SearchRepositoryImpl
import com.smsman.playlistmaker.search.domain.HistoryRepository
import com.smsman.playlistmaker.search.domain.HistoryUseCase
import com.smsman.playlistmaker.search.domain.HistoryUseCaseImpl
import com.smsman.playlistmaker.search.domain.SearchRepository
import com.smsman.playlistmaker.search.domain.SearchUseCase
import com.smsman.playlistmaker.search.domain.SearchUseCaseImpl
import com.smsman.playlistmaker.settings.data.SettingsInteractorImpl
import com.smsman.playlistmaker.settings.data.SettingsRepositoryImpl
import com.smsman.playlistmaker.settings.domain.SettingsInteractor
import com.smsman.playlistmaker.settings.domain.SettingsRepository
import com.smsman.playlistmaker.sharing.domain.SharingRepository
import com.smsman.playlistmaker.sharing.data.SharingRepositoryImpl
import com.smsman.playlistmaker.sharing.domain.SharingInteractor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Creator {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val itunesSearchApi = retrofit.create(ItunesSearchApi::class.java)

    fun createMediaRepository(intent: Intent): MediaRepository {
        return MediaDataSource(intent)
    }
    fun createInteractor(audioUrl: String?): PlayerInteractor {
        val player = PlayerImpl(audioUrl)
        return PlayerInteractorImpl(player)
    }

    fun createSharingInteractor(sharingRepository: SharingRepository): SharingInteractor {
        return SharingInteractor(sharingRepository)
    }
    fun createHistoryUseCase(historyRepository: HistoryRepository): HistoryUseCase {
        return HistoryUseCaseImpl(historyRepository)
    }
    fun createHistoryRepository(sharedPreferences: SharedPreferences): HistoryRepository {
        return HistoryRepositoryImpl(sharedPreferences)
    }
    fun createSearchRepository(itunesSearchApi: ItunesSearchApi): SearchRepository {
        return SearchRepositoryImpl(itunesSearchApi)
    }
    fun createSearchUseCase(searchRepository: SearchRepository): SearchUseCase {
        return SearchUseCaseImpl(searchRepository)
    }

    fun createSettingsRepository(sharedPrefs: SharedPreferences): SettingsRepository {
        return SettingsRepositoryImpl(sharedPrefs)
    }

    fun createSharingRepository(context: Context): SharingRepository {
        return SharingRepositoryImpl(context)
    }

    fun createSettingsInteractor(settingsRepository: SettingsRepository, sharingRepository: SharingRepository): SettingsInteractor {
        return SettingsInteractorImpl(settingsRepository, sharingRepository)
    }

}