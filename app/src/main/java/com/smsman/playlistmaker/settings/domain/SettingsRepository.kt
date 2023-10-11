package com.smsman.playlistmaker.settings.domain

interface SettingsRepository {
    fun getDarkTheme(): Boolean
    fun setDarkTheme(enabled: Boolean)
    fun setAppTheme()
}