package com.selincengiz.ghibli.ui.search

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.databinding.FragmentSearchBinding
import com.selincengiz.ghibli.domain.entities.Tv
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), ItemSliderListener, ItemTvListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter by lazy { SliderAdapter(this) }
    private val adapterTv by lazy { TvAdapter(this) }
   private val adapterOnTheAirTv by lazy { TvAdapter(this) }
    private val slideHandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewPager()
        binding.trendingRecycler.adapter = adapterTv
       binding.ontheairRecycler.adapter = adapterOnTheAirTv


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDiscoverTv()
        viewModel.getPopularTv()
        viewModel.getOnTheAirTv()
        observe()

    }

    fun viewPager() = with(binding) {
        viewPager.adapter = adapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(40))
        pageTransformer.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }

        })

        viewPager.setPageTransformer(pageTransformer)
        viewPager.currentItem = 1
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                slideHandler.removeCallbacks(slideRunnable)

            }

        })


    }

    val slideRunnable = Runnable {
        kotlin.run {
            binding.viewPager.currentItem = binding.viewPager.currentItem
        }

    }

    override fun onPause() {
        super.onPause()
        slideHandler.removeCallbacks(slideRunnable)
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(slideRunnable, 2000)
    }

    fun observe() {

        viewModel.searchState.observe(viewLifecycleOwner) { state ->

            when (state) {
                SearchState.Loading -> {

                    binding.progressLayout.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is SearchState.Discover -> {
                    adapter.submitList(state.tv.subList(0, 10))
                    binding.progressLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                is SearchState.Popular -> {
                    adapterTv.submitList(state.tv.subList(0, 20))
                    binding.progressLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                is SearchState.OnTheAir -> {
                  adapterOnTheAirTv.submitList(state.tv.subList(0, 20))
                    binding.progressLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                is SearchState.Error -> {
                    binding.progressLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                    Log.i("errorhttp", state.throwable.message!!)

                }

                else -> {

                }
            }
        }

    }

    override fun onClicked(discoverTv: Tv) {

    }


}