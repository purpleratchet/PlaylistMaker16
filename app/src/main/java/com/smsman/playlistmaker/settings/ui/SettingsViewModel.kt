package com.smsman.playlistmaker.settings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smsman.playlistmaker.settings.domain.SettingsInteractor
import com.smsman.playlistmaker.sharing.domain.SharingInteractor


class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor,
    private val sharingInteractor: SharingInteractor
) : ViewModel() {
    private val darkThemeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getDarkThemeLiveData(): LiveData<Boolean> {
        return darkThemeLiveData
    }

    fun setDarkTheme(isDarkTheme: Boolean) {
        settingsInteractor.setDarkTheme(isDarkTheme)
        darkThemeLiveData.postValue(isDarkTheme)
    }

    fun getDarkTheme(): Boolean {
        return settingsInteractor.getDarkTheme()
    }

    fun shareApp() {
        sharingInteractor.shareApp()
    }

    fun sendSupportEmail() {
        sharingInteractor.sendSupportEmail()
    }

    fun openAgreementUrl() {
        sharingInteractor.openAgreementUrl()
    }

    fun setAppTheme() {
        settingsInteractor.setAppTheme()

    }
}