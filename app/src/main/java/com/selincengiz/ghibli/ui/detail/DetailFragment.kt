package com.selincengiz.ghibli.ui.detail

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.Constants
import com.selincengiz.ghibli.common.DetailState
import com.selincengiz.ghibli.common.Extensions.loadUrl
import com.selincengiz.ghibli.data.entities.Genre
import com.selincengiz.ghibli.databinding.FragmentDetailBinding
import com.selincengiz.ghibli.domain.entities.TvVideo
import com.selincengiz.ghibli.ui.video.VideoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(), ItemTvListener, ItemVideoListener {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val adapter by lazy { CategoryAdapter(this) }
    private val adapterVideo by lazy { VideoAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.recyclerView.adapter = adapter
        binding.videoRecycler.adapter = adapterVideo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTvDetail(args.tv.id!!)
        viewModel.getVideoTv(args.tv.id!!)
        observe()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun observe() = with(binding) {
        viewModel.detailState.observe(viewLifecycleOwner) { state ->

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
                }

                is DetailState.Video -> {
                    val list: List<TvVideo> = state.videos.filter {
                        it.site == "YouTube"
                    }.map { it ->
                        it.copy(photo = args.tv.posterPath)
                    }

                    adapterVideo.submitList(list)
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

    override fun onClicked(genre: Genre) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClicked(video: TvVideo) {
        // Fragment'ı oluşturun
     // requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)


        val fragment = VideoFragment(video)

  //  requireActivity().findViewById<MotionLayout>(R.id.motion_layout).setTransition(R.id.expanded,R.id.collapsed)
        // Fragment'ı FrameLayout içine ekleyin
       requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()




    }


}