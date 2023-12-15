package com.selincengiz.ghibli.ui.seek

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.common.SeekState
import com.selincengiz.ghibli.databinding.FragmentSeekBinding
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.ui.search.SliderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeekFragment : Fragment(), ItemListener, SearchView.OnQueryTextListener {


    private lateinit var binding: FragmentSeekBinding
    private val viewModel by viewModels<SeekViewModel>()
    private val adapter by lazy { SearchAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_seek, container, false)
        binding.recyclerSeek.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDefaultTv()
        observe()
        binding.searchView.setOnQueryTextListener(this)
    }

    fun observe() {
        viewModel.seekState.observe(viewLifecycleOwner) { state ->

            when (state) {
                SeekState.Loading -> {

                    binding.progressLayout.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is SeekState.Seek -> {
                    adapter.submitList(state.tv.subList(0, 15))
                    binding.progressLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }


                is SeekState.Error -> {
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

    override fun onClicked(tv: Tv) {
        findNavController().navigate(SeekFragmentDirections.seekToDetail(tv))
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        text?.let {
            if (it.length > 2) {
                viewModel.getSeekTv(it)
            }
        }
        return true
    }

    override fun onQueryTextChange(text: String?): Boolean {
        text?.let {
            if (it.length > 2) {
                viewModel.getSeekTv(it)
            }
        }
        return true
    }


}