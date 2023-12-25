package com.selincengiz.ghibli.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.databinding.FragmentViewBinding



class ViewFragment : Fragment() {

    private lateinit var  binding :FragmentViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_view, container, false)

        return binding.root
    }


}