package com.smsman.playlistmaker.creator

import android.content.Context
import android.media.MediaPlayer
import com.smsman.playlistmaker.player.data.PlayerRepositoryImpl
import com.smsman.playlistmaker.player.domain.api.PlayerInteractor
import com.smsman.playlistmaker.player.domain.api.PlayerRepository
import com.smsman.playlistmaker.player.domain.impl.PlayerInteractorImpl
import com.smsman.playlistmaker.search.data.SearchRepositoryImpl
import com.smsman.playlistmaker.search.data.network.RetrofitNetworkClient
import com.smsman.playlistmaker.search.domain.SearchInteractor
import com.smsman.playlistmaker.search.domain.SearchRepository
import com.smsman.playlistmaker.search.domain.impl.SearchInteractorImpl
import com.smsman.playlistmaker.settings.data.SettingsInteractorImpl
import com.smsman.playlistmaker.settings.data.SettingsRepositoryImpl
import com.smsman.playlistmaker.settings.domain.SettingsInteractor
import com.smsman.playlistmaker.settings.domain.SettingsRepository
import com.smsman.playlistmaker.settings.ui.SettingsViewModelFactory
import com.smsman.playlistmaker.sharing.domain.SharingRepository
import com.smsman.playlistmaker.sharing.data.SharingRepositoryImpl
import com.smsman.playlistmaker.sharing.domain.SharingInteractor

object Creator {

    fun providePlayerInteractor(): PlayerInteractor {
        return PlayerInteractorImpl(providePlayerRepository(MediaPlayer()))
    }

    fun providePlayerRepository(Player: MediaPlayer): PlayerRepository {
        return PlayerRepositoryImpl(Player)
    }

    fun createSharingInteractor(sharingRepository: SharingRepository): SharingInteractor {
        return SharingInteractor(sharingRepository)
    }

    fun createSharingRepository(context: Context): SharingRepository {
        return SharingRepositoryImpl(context)
    }

    fun createSettingsInteractor(
        settingsRepository: SettingsRepository,
        sharingRepository: SharingRepository
    ): SettingsInteractor {
        return SettingsInteractorImpl(settingsRepository, sharingRepository)
    }

    fun createSettingsRepository(context: Context): SettingsRepository {
        val sharedPrefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return SettingsRepositoryImpl(sharedPrefs)
    }

    fun createSettingsViewModelFactory(context: Context): SettingsViewModelFactory {
        val settingsRepository = createSettingsRepository(context)
        val sharingRepository = createSharingRepository(context)
        val settingsInteractor = createSettingsInteractor(settingsRepository, sharingRepository)
        val sharingInteractor = createSharingInteractor(sharingRepository)
        return SettingsViewModelFactory(settingsInteractor, sharingInteractor)
    }

    fun provideSearchRepository(context: Context): SearchRepository {
        return SearchRepositoryImpl(
            RetrofitNetworkClient(context),
            com.smsman.playlistmaker.search.data.sharedPrefs.SharedPrefsSearchDataStorage(context),
        )
    }

    fun provideSearchInteractor(context: Context): SearchInteractor {
        return SearchInteractorImpl(provideSearchRepository(context))
    }
}