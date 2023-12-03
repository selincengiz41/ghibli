package com.selincengiz.ghibli.ui.register

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.LoginState
import com.selincengiz.ghibli.common.RegisterState
import com.selincengiz.ghibli.databinding.FragmentRegisterBinding
import com.selincengiz.ghibli.ui.login.LoginFragmentDirections
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.registerToLogin())
        }

        binding.buttonRegister.setOnClickListener {
            registerClicked()
        }
        observe()
    }

    fun observe() {
        viewModel.registerState.observe(viewLifecycleOwner) { state ->

            when (state) {
                is RegisterState.Loading -> {
                    binding.progressBarRegister.visibility = View.VISIBLE
                }

                is RegisterState.Registered -> {
                    binding.progressBarRegister.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()

                    // Delay süresi sonunda başka bir işlemi gerçekleştirmek için Handler kullan
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        // Bu kısımda gecikme sonunda gerçekleştirilmesi istenen işlemleri yapabilirsiniz
                        // Örneğin, fragment içinde başka bir işlemi gerçekleştirmek ya da navigasyon yapmak
                        findNavController().navigate(RegisterFragmentDirections.registerToSearch())

                    }, 1000)

                }

                is RegisterState.Error -> {
                    binding.progressBarRegister.visibility = View.GONE
                    Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun registerClicked() {
        viewModel.firebaseRegister(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString(),
            binding.etPasswordConfirm.text.toString(),
            binding.etName.text.toString()
        )
    }


}