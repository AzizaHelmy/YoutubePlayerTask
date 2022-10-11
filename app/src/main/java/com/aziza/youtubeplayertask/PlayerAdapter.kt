package com.aziza.youtubeplayertask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.aziza.youtubeplayertask.databinding.ItemRvPlayerBinding

class PlayerAdapter() : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    private val playList: List<Player> = ArrayList()
    private var player: ExoPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            ItemRvPlayerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    inner class PlayerViewHolder(private val binding: ItemRvPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var player: ExoPlayer? = null
        fun bind() {
            player = ExoPlayer.Builder(binding.playerView.context)
                // .setTrackSelector(trackSelector)
                .build()
                .also { exoPlayer ->
                    binding.playerView.player = exoPlayer
                }
        }
    }

}