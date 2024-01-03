package com.selincengiz.ghibli.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.LoginState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private var _loginState = MutableStateFlow<LoginState>(LoginState.Entry)
    val loginState: StateFlow<LoginState>
        get() = _loginState.asStateFlow()

    fun firebaseLogin(email: String?, password: String?) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = loginUseCase.invoke(email, password)
            when (result) {
                is Resource.Success -> {
                    _loginState.value = LoginState.Logined(result.data)
                }

                is Resource.Error -> {
                    _loginState.value = LoginState.Error(result.throwable)
                }
            }
        }
    }
}