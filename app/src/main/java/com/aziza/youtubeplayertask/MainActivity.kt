package com.aziza.youtubeplayertask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.Player
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.recyclerview.widget.GridLayoutManager
import com.aziza.youtubeplayertask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val playerAdapter by lazy {
        PlayerAdapter()
    }
    private var playList = ArrayList<VideoPlayer>()

  private val playbackStateListener: Player.Listener = playbackStateListener()
    private var player: ExoPlayer? = null
    private var player2: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialzeRecyclerView()
//        initializePlayer(getString(R.string.media_url_mp4))
//        playList.add(VideoPlayer(player!!))
//        //playList.add(VideoPlayer(player2!!))
//        playList.add(VideoPlayer(player!!))
//        // playList.add(VideoPlayer(player2!!))
//        playList.add(VideoPlayer(player!!))

    }

    private fun initializePlayer(uri: String) {
        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = ExoPlayer.Builder(this)
             .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                // binding.playerView.player = exoPlayer

                val mediaItem = MediaItem.Builder()
                    .setUri(uri)
                    .build()
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = false // i will play it
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }
        playList.add(VideoPlayer(player!!))
        playList.add(VideoPlayer(player!!))
        playList.add(VideoPlayer(player!!))
        playList.add(VideoPlayer(player!!))
        playList.add(VideoPlayer(player!!))

    }

    private fun initialzeRecyclerView() {
        binding.videosRv.apply {
            var layoutManager =
                GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
            setLayoutManager(layoutManager)
            playerAdapter.submitLis(playList)
            adapter = playerAdapter
        }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer(getString(R.string.media_url_mp4))
        }
    }

    public override fun onResume() {
        super.onResume()
        //hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer(getString(R.string.media_url_mp4))
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            initializePlayer(getString(R.string.media_url_mp4))
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            //releasePlayer()
        }
    }

    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Log.e("TAG", "changed state to $stateString")
        }
    }
}
