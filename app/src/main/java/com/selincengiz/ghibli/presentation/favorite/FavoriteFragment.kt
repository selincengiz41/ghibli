package com.selincengiz.ghibli.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.FavoriteState
import com.selincengiz.ghibli.databinding.FragmentFavoriteBinding
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.presentation.search.ItemTvListener
import com.selincengiz.ghibli.presentation.search.TvAdapter
import com.selincengiz.ghibli.presentation.favorite.FavoriteFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoriteFragment : Fragment(), ItemTvListener {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapterTv by lazy { TvAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.recyclerFavorite.adapter = adapterTv
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavorites()
        observe()
    }

    fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.favoriteState.collectLatest  { state ->

                when (state) {
                    FavoriteState.Loading -> {

                        binding.progressLayout.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is FavoriteState.Favorite -> {
                        adapterTv.submitList(state.tv)
                        binding.progressLayout.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }


                    is FavoriteState.Error -> {
                        binding.progressLayout.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                            .show()

                    }

                    else -> {

                    }
                }
            }
        }


    }

    override fun onClicked(tv: Tv) {
        findNavController().navigate(FavoriteFragmentDirections.favoriteToDetail(tv))
    }
}