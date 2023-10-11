package com.smsman.playlistmaker.player.ui


interface MediaContract {
    interface Presenter {
        fun onPlayClicked()
        fun onPauseAudioClicked()
        fun onFavoriteClicked()
        fun onDisLikeClicked()
        fun onPause()
    }
}