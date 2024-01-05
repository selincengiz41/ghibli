package com.selincengiz.ghibli.presentation.detail


import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.Constants
import com.selincengiz.ghibli.common.DetailState
import com.selincengiz.ghibli.common.Extensions.loadUrl
import com.selincengiz.ghibli.common.IsFullScreen.isFullScreen
import com.selincengiz.ghibli.data.entities.Genre
import com.selincengiz.ghibli.databinding.FragmentDetailBinding
import com.selincengiz.ghibli.domain.entities.TvVideo
import com.selincengiz.ghibli.domain.mapper.mapToFavoriteTv
import com.selincengiz.ghibli.presentation.video.VideoFragment
import com.selincengiz.ghibli.presentation.detail.DetailFragmentArgs
import com.selincengiz.ghibli.ui.ClickableMotionLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class DetailFragment : Fragment(), ItemTvListener, ItemVideoListener {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val adapter by lazy { CategoryAdapter(this) }
    private val adapterVideo by lazy { VideoAdapter(this) }
    private lateinit var  myIcon:Drawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.selincengiz.ghibli.R.layout.fragment_detail, container, false)
        binding.recyclerView.adapter = adapter
        binding.videoRecycler.adapter = adapterVideo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTvDetail(args.tv.id!!)
        viewModel.getVideoTv(args.tv.id!!)

        observe()

         myIcon = resources.getDrawable(com.selincengiz.ghibli.R.drawable.faved)
        val filter: ColorFilter = LightingColorFilter(Color.YELLOW, Color.YELLOW)
        myIcon.colorFilter = filter


        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.favButton.setOnClickListener {
            if(binding.isFavorite!!){
                viewModel.deleteFavorite(args.tv.mapToFavoriteTv())
                binding.isFavorite=false
                val icon = resources.getDrawable(com.selincengiz.ghibli.R.drawable.fav)

                binding.favButton.setImageDrawable(icon)
            }
            else{
                viewModel.addFavorite(args.tv.mapToFavoriteTv())
                binding.isFavorite=true
                binding.favButton.setImageDrawable(myIcon)

            }
            binding.favButton.requestLayout()
        }
    }

     fun observe() = with(binding) {
         lifecycleScope.launchWhenStarted {
             viewModel.detailState.collectLatest { state ->

                 when (state) {
                     DetailState.Loading -> {

                         detailLayout.visibility = View.GONE
                         progressBar2.visibility = View.VISIBLE
                     }

                     is DetailState.Tv -> {
                         adapter.submitList(state.tv.genres)
                         tvName.text = state.tv.name
                         tvPoster.loadUrl(Constants.IMAGE_URL + state.tv.posterPath)
                         tvSummary.text = state.tv.overview
                         tvRate.text = state.tv.voteAverage.toString()
                         tvHour.text = state.tv.firstAirDate
                         detailLayout.visibility = View.VISIBLE
                         progressBar2.visibility = View.GONE
                         viewModel.isFavorite(state.tv.id!!)
                     }

                     is DetailState.IsFavorite ->{
                         detailLayout.visibility = View.VISIBLE
                         progressBar2.visibility = View.GONE
                         isFavorite=state.favorite
                         if(state.favorite!!)
                         {

                             binding.favButton.setImageDrawable(myIcon)
                         }
                         else{
                             val icon = resources.getDrawable(com.selincengiz.ghibli.R.drawable.fav)

                             binding.favButton.setImageDrawable(icon)
                         }


                     }

                     is DetailState.Video -> {
                         val list: List<TvVideo> = state.videos.filter {
                             it.site == "YouTube"
                         }.map { it ->
                             it.copy(photo = args.tv.posterPath)
                         }

                         adapterVideo.submitList(list)
                         detailLayout.visibility = View.VISIBLE
                         progressBar2.visibility = View.GONE
                     }


                     is DetailState.Error -> {
                         detailLayout.visibility = View.VISIBLE
                         progressBar2.visibility = View.GONE
                         Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                             .show()
                         Log.i("eor", state.throwable.message!!)

                     }

                     else -> {

                     }
                 }
             }
         }

    }

    override fun onClicked(genre: Genre) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClicked(video: TvVideo) {

        val fragment = VideoFragment(video)


       requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

     requireActivity().findViewById<ClickableMotionLayout>(R.id.motion_layout).transitionToStart()
        isFullScreen=true





    }


}