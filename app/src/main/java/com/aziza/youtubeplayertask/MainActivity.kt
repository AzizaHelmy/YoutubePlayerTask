package com.aziza.youtubeplayertask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aziza.youtubeplayertask.databinding.ActivityMainBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView


class MainActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecyclerAdapter:YoutubeRecyclerAdapter
    companion object {
        const val API_KEY = "AIzaSyC2Rvx781wSegGbxPaqQrsD1cb7Hpy4kB4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val youtubeVideos = prepareList()
        mRecyclerAdapter= YoutubeRecyclerAdapter(youtubeVideos)
        binding.recyclerViewFeed.adapter=mRecyclerAdapter

    }
    private fun prepareList(): List<YoutubeVideo> {
        val videoArrayList = ArrayList<YoutubeVideo>()
        // add first item
        val video1 = YoutubeVideo()
        video1.id = 1L
        video1.imageUrl = "https://i.ytimg.com/vi/XJ1WLZiKKMg/maxresdefault.jpg"
        video1.videoId = "XJ1WLZiKKMg"
        // add second item
        val video2 = YoutubeVideo()
        video2.id = 2L
        video2.imageUrl = "https://i.ytimg.com/vi/B95f2fd9bH0/maxresdefault.jpg"
        video2.videoId = "B95f2fd9bH0"
        // add third item
        val video3 = YoutubeVideo()
        video3.id = 3L
        video3.imageUrl = "https://i.ytimg.com/vi/5tUY56-dOBo/hqdefault.jpg"
        video3.videoId = "5tUY56-dOBo"
        // add four item
        val video4 = YoutubeVideo()
        video4.id = 4L
        video4.imageUrl = "https://i.ytimg.com/vi/9r9QT2dw34o/maxresdefault.jpg"
        video4.videoId = "9r9QT2dw34o"
        // add five item
        val video5 = YoutubeVideo()
        video5.id = 5L
        video5.imageUrl = "https://i.ytimg.com/vi/PXZVQv3t-Y4/maxresdefault.jpg"
        video5.videoId = "PXZVQv3t-Y4"

        videoArrayList.add(video3)
        videoArrayList.add(video2)
        videoArrayList.add(video1)
        videoArrayList.add(video4)
        videoArrayList.add(video5)
        return videoArrayList
    }


}