package com.smsman.playlistmaker.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.smsman.playlistmaker.R
import com.smsman.playlistmaker.search.domain.ItunesSearchResult

class TrackAdapter(
    private val data: List<ItunesSearchResult>,
    private val listener: (Any) -> Unit
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            listener.invoke(data[position])
            animateItemClick(holder.itemView)
        }
    }

    override fun getItemCount() = data.size

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackName: TextView = itemView.findViewById(R.id.track_name)
        private val artistName: TextView = itemView.findViewById(R.id.artist_name)
        private val trackTime: TextView = itemView.findViewById(R.id.track_time)
        private val trackArtwork: ImageView = itemView.findViewById(R.id.track_artwork)

        fun bind(track: ItunesSearchResult) {
            trackName.text = track.trackName
            artistName.text = track.artistName
            val duration = (track.trackTimeMillis.toLong() / 1000).toInt()
            trackTime.text = TimeUtils.formatTime(duration)
            Glide.with(itemView.context)
                .load(track.artworkUrl100)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                .into(trackArtwork)
        }
    }

    private fun animateItemClick(view: View) {
        val anim = AnimationUtils.loadAnimation(view.context, R.anim.click_animation)
        view.startAnimation(anim)
    }

    class TimeUtils {
        companion object {
            fun formatTime(duration: Int): String {
                val minutes = duration / 60
                val seconds = duration % 60
                return String.format("%02d:%02d", minutes, seconds)
            }
        }
    }
}