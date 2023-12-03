package com.selincengiz.ghibli.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.LoginState
import com.selincengiz.ghibli.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.loginToRegister())
        }
        binding.buttonLogin.setOnClickListener {
            loginClicked()
        }
        observe()

    }

    fun observe() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->

            when (state) {
                is LoginState.Loading -> {
                    binding.progressBarLogin.visibility = View.VISIBLE
                }

                is LoginState.Logined -> {
                    binding.progressBarLogin.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()

                    // Delay süresi sonunda başka bir işlemi gerçekleştirmek için Handler kullan
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        // Bu kısımda gecikme sonunda gerçekleştirilmesi istenen işlemleri yapabilirsiniz
                        // Örneğin, fragment içinde başka bir işlemi gerçekleştirmek ya da navigasyon yapmak
                        findNavController().navigate(LoginFragmentDirections.loginToSearch())

                    }, 1000)

                }

                is LoginState.Error -> {
                    binding.progressBarLogin.visibility = View.GONE
                    Toast.makeText(requireContext(), state.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }

    fun loginClicked() {
        viewModel.firebaseLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
    }

}