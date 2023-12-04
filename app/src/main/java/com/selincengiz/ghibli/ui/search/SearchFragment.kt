package com.selincengiz.ghibli.ui.search

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.databinding.FragmentSearchBinding
import com.selincengiz.ghibli.domain.entities.Tv
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class SearchFragment : Fragment(), ItemSliderListener, ItemTvListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter by lazy { SliderAdapter(this) }
    private val adapterTv by lazy { TvAdapter(this) }
    private val adapterOnTheAirTv by lazy { TvAdapter(this) }
    private val slideHandler = Handler()
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed

    val PERIOD_MS: Long = 5000 // time in milliseconds between successive task executions.


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

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                currentPage = position
            }


        })

        /*After setting the adapter use the timer */



        timer = Timer() // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)


    }
    val handler = Handler()
    val Update = Runnable {
        if (currentPage != adapter.itemCount - 1) {
            currentPage++
        } else {
            currentPage = 0
        }
        binding.viewPager.setCurrentItem(currentPage, true)
    }
    val slideRunnable = Runnable {
        kotlin.run {
            binding.viewPager.currentItem = binding.viewPager.currentItem
        }

    }

    override fun onPause() {
        super.onPause()
        slideHandler.removeCallbacks(slideRunnable)
        handler.removeCallbacks(Update)
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(slideRunnable, 2000)
        handler.postDelayed(Update,2000)
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

    override fun onClicked(tv: Tv) {

        findNavController().navigate(SearchFragmentDirections.searchToDetail(tv))
    }


}