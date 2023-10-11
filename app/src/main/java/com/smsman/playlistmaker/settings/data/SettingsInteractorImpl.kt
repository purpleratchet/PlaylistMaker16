package com.smsman.playlistmaker.settings.data

import com.smsman.playlistmaker.settings.domain.SettingsInteractor
import com.smsman.playlistmaker.settings.domain.SettingsRepository
import com.smsman.playlistmaker.sharing.domain.SharingRepository

class SettingsInteractorImpl(
    private val settingsRepository: SettingsRepository,
    private val sharingRepository: SharingRepository
) :
    SettingsInteractor {
    override fun getDarkTheme(): Boolean {
        return settingsRepository.getDarkTheme()
    }

    override fun setDarkTheme(enabled: Boolean) {
        settingsRepository.setDarkTheme(enabled)
    }

    override fun shareApp() {
        sharingRepository.shareApp()
    }

    override fun sendSupportEmail() {
        sharingRepository.sendSupportEmail()
    }

    override fun openAgreementUrl() {
        sharingRepository.openAgreementUrl()
    }

    override fun setAppTheme() {
        settingsRepository.setAppTheme()
    }
}