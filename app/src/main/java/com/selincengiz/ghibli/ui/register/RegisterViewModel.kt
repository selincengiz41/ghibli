package com.selincengiz.ghibli.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.selincengiz.ghibli.common.LoginState
import com.selincengiz.ghibli.common.RegisterState
import com.selincengiz.ghibli.common.Resource

import com.selincengiz.ghibli.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    private var _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState>
        get() = _registerState

    fun firebaseRegister(
        email: String?,
        password: String?,
        passwordConfirm: String?,
        name: String?
    ) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val result = registerUseCase.invoke(email, password, passwordConfirm, name)
            when (result) {
                is Resource.Success -> {
                    _registerState.value = RegisterState.Registered(result.data)
                }

                is Resource.Error -> {
                    _registerState.value = RegisterState.Error(result.throwable)
                }
            }
        }
    }


}