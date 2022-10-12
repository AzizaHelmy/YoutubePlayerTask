package com.aziza.youtubeplayertask

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aziza.youtubeplayertask.databinding.ItemRvPlayerBinding
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector

class PlayerAdapter(context: Context) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    private var playList: List<VideoPlayer> = ArrayList()

    init {

    }

    fun submitLis(newList: List<VideoPlayer>) {
        playList = newList
        notifyDataSetChanged()
    }

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
        holder.bind(playList[position])
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    inner class PlayerViewHolder(val binding: ItemRvPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
     //  private val playbackStateListener: Player.Listener = playbackStateListener()

        private var player: ExoPlayer? = null
        private var playWhenReady = true
        private var currentItem = 0
        private var playbackPosition = 0L

        fun bind(item: VideoPlayer) {
//            player = ExoPlayer.Builder(binding.playerView.context)
//                // .setTrackSelector(trackSelector)
//                .build()
//                .also { exoPlayer ->
//                    binding.playerView.player = item.player
//                }
            initializePlayer(item.player)
        }

        fun initializePlayer(player: ExoPlayer?) {
            val trackSelector = DefaultTrackSelector().apply {
                setParameters(buildUponParameters().setMaxVideoSizeSd())
            }
            this.player = ExoPlayer.Builder(binding.playerView.context)
                .setTrackSelector(trackSelector)
                .build()
                .also { exoPlayer ->
                    binding.playerView.player = player

                    val mediaItem = MediaItem.Builder()
                        .setUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
                        // .setMimeType(MimeTypes.APPLICATION_MPD)
                        .build()
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.playWhenReady = false // i will play it
                    exoPlayer.seekTo(currentItem, playbackPosition)
                  // exoPlayer.addListener(playbackStateListener)
                    exoPlayer.prepare()
                }
        }
    }

}