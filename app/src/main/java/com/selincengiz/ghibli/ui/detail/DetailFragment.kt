package com.selincengiz.ghibli.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.Constants
import com.selincengiz.ghibli.common.DetailState
import com.selincengiz.ghibli.common.Extensions.loadUrl
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.data.entities.Genre
import com.selincengiz.ghibli.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), ItemTvListener {

    private lateinit var binding :FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val adapter by lazy { CategoryAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.recyclerView.adapter=adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTvDetail(args.tv.id!!)
        observe()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun observe() = with(binding){
        viewModel.detailState.observe(viewLifecycleOwner){state->

            when (state) {
                DetailState.Loading -> {

                    detailLayout.visibility = View.GONE
                    progressBar2.visibility = View.VISIBLE
                }

                is DetailState.Tv -> {
                    adapter.submitList(state.tv.genres)
                    tvName.text=state.tv.name
                    tvPoster.loadUrl(Constants.IMAGE_URL +state.tv.posterPath)
                    tvSummary.text=state.tv.overview
                    tvRate.text=state.tv.voteAverage.toString()
                    tvHour.text=state.tv.firstAirDate
                    detailLayout.visibility = View.VISIBLE
                    progressBar2.visibility = View.GONE
                }


                is DetailState.Error -> {
                    detailLayout.visibility = View.VISIBLE
                    progressBar2.visibility = View.GONE
                    Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                    Log.i("eor",state.throwable.message!!)

                }

                else -> {

                }
            }
        }
    }

    override fun onClicked(genre: Genre) {

    }


}