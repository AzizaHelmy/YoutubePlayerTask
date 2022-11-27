package com.aziza.youtubeplayertask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aziza.youtubeplayertask.databinding.ItemYoutubeListBinding
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

/**
 * Created by Aziza Helmy on 11/24/2022.
 */
class YoutubeRecyclerAdapter(private val youtubeVideos: List<YoutubeVideo>) :
    RecyclerView.Adapter<YoutubeRecyclerAdapter.YoutubeRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeRecyclerViewHolder {
        return YoutubeRecyclerViewHolder(ItemYoutubeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: YoutubeRecyclerViewHolder, position: Int) {
        holder.bind(youtubeVideos[position])
    }

    override fun getItemCount(): Int = youtubeVideos.size

    inner class YoutubeRecyclerViewHolder(private val binding: ItemYoutubeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YoutubeVideo) {
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .into(binding.imageViewItem)
            binding.btnPlay.setOnClickListener {
                binding.imageViewItem.visibility = View.GONE
                binding.ytPlayer.visibility = View.VISIBLE
                binding.btnPlay.visibility = View.GONE

                binding.ytPlayer.initialize(MainActivity.API_KEY, object :
                    YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubePlayer?,
                        p2: Boolean
                    ) {
                        p1?.loadVideo(item.videoId)
                        p1?.play()
                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Toast.makeText(
                            binding.btnPlay.context,
                            "Video player Failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }

                })
            }
        }

    }

}





