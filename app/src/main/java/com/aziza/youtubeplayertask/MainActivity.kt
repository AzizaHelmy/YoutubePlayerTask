package com.aziza.youtubeplayertask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aziza.youtubeplayertask.databinding.ActivityMainBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView


class MainActivity : AppCompatActivity() {
    //    private val youTubePlayerView= YouTubePlayer()
//    private val binding by lazy(LazyThreadSafetyMode.NONE) {
//        ActivityMainBinding.inflate(layoutInflater)
//    }
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val API_KEY = "AIzaSyC2Rvx781wSegGbxPaqQrsD1cb7Hpy4kB4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get reference to the view of Video player
        val ytPlayer = binding.ytPlayer as YouTubePlayerView
        ytPlayer.initialize(API_KEY, object : OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo("HzeK7g8cD0Y")
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT)
                    .show();

            }

        })
    }



}