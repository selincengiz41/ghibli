package com.selincengiz.ghibli.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.LoginState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private var _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState>
        get() = _loginState

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