package com.selincengiz.ghibli.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.databinding.FragmentVideoBinding
import com.selincengiz.ghibli.domain.entities.TvVideo


class VideoFragment(private val video: TvVideo) : Fragment() {

   private lateinit var binding:FragmentVideoBinding
  // private val args by navArgs<VideoFragmentArgs>()
    private lateinit var rtspUrl: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_video, container, false)

 return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {


            lifecycle.addObserver(youtubePlayerView)
           youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {

                    youTubePlayer.loadVideo(video.key!!, 0f)
                }

                override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                    super.onError(youTubePlayer, error)
                    Toast.makeText(requireContext(), error.name, Toast.LENGTH_SHORT).show()
                }

               override fun onStateChange(
                   youTubePlayer: YouTubePlayer,
                   state: PlayerConstants.PlayerState
               ) {
                   super.onStateChange(youTubePlayer, state)
                   requireActivity().window!!.decorView.apply {

                       systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
                   }
               }
            })

            requireActivity().window!!.decorView.apply {

                systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            }



        }
    }




    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release();
    }




}