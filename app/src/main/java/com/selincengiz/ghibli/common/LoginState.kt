package com.selincengiz.ghibli.common

import com.selincengiz.ghibli.domain.entities.Tv

sealed interface LoginState{
    object Loading : LoginState
    data class Logined(val message: String) : LoginState
    data class Error(val throwable: Throwable) : LoginState

}