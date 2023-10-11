package com.smsman.playlistmaker.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smsman.playlistmaker.settings.domain.SettingsInteractor
import com.smsman.playlistmaker.sharing.domain.SharingInteractor


class SettingsViewModelFactory(
    private val settingsInteractor: SettingsInteractor,
    private val sharingInteractor: SharingInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(settingsInteractor, sharingInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}