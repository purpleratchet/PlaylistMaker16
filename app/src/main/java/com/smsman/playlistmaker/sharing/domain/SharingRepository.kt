package com.smsman.playlistmaker.sharing.domain

interface SharingRepository {
    fun shareApp()
    fun sendSupportEmail()
    fun openAgreementUrl()
}