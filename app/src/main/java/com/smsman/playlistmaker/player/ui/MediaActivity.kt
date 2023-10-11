package com.smsman.playlistmaker.player.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.smsman.playlistmaker.creator.Creator
import com.smsman.playlistmaker.R


class MediaActivity : AppCompatActivity() {
    private lateinit var viewModel: MediaViewModel

    companion object {
        const val EXTRA_PREVIEW = "previewUrl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        val intent = intent
        val repository = Creator.createMediaRepository(intent)

        viewModel = ViewModelProvider(
            this,
            MediaViewModelFactory(repository)
        ).get(MediaViewModel::class.java)

        setupViews()
        observeViewModel()

        viewModel.initialize(
            findViewById(R.id.btnPlay),
            findViewById(R.id.btnPause),
            findViewById(R.id.progressTime),
            findViewById(R.id.btnFavorite),
            findViewById(R.id.btnDisLike),
            intent.getStringExtra(EXTRA_PREVIEW),
            Creator.createInteractor(intent.getStringExtra(EXTRA_PREVIEW))
        )
    }

    private fun setupViews() {
        findViewById<ImageButton>(R.id.btnPlay).setOnClickListener { viewModel.onPlayClicked() }
        findViewById<ImageButton>(R.id.btnPause).setOnClickListener { viewModel.onPauseAudioClicked() }
        findViewById<ImageButton>(R.id.btnFavorite).setOnClickListener { viewModel.onFavoriteClicked() }
        findViewById<ImageButton>(R.id.btnDisLike).setOnClickListener { viewModel.onDisLikeClicked() }
        findViewById<Button>(R.id.btnPlayerBack).setOnClickListener { finish() }
    }

    private fun observeViewModel() {
        viewModel.mediaViewState.observe(this) { state ->
            state.trackCoverUrl?.let { trackCoverUrl ->
                val radius = resources.getDimensionPixelSize(R.dimen.cover_radius).toFloat()
                Glide.with(this)
                    .load(MediaViewModel.getCoverArtwork(trackCoverUrl))
                    .transform(RoundedCorners(radius.toInt()))
                    .placeholder(R.drawable.placeholder)
                    .into(findViewById(R.id.track_cover))
            }
            findViewById<TextView>(R.id.trackNameResult).text = state.trackName
            findViewById<TextView>(R.id.artistNameResult).text = state.artistName
            findViewById<TextView>(R.id.trackTimeResult).text = state.trackTime?.let {MediaViewModel.formatTrackDuration(it)}
            findViewById<TextView>(R.id.collection_Name).text = state.collectionName
            findViewById<TextView>(R.id.release_Date).text = state.releaseDate?.let { MediaViewModel.formatReleaseDate(it) }
            findViewById<TextView>(R.id.primary_GenreName).text = state.primaryGenreName
            findViewById<TextView>(R.id.country).text = state.country
            findViewById<TextView>(R.id.progressTime).text = state.progressTime
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }
}