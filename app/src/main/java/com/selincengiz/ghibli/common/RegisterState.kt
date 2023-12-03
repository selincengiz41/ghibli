package com.selincengiz.ghibli.common

sealed interface RegisterState{
    object Loading : RegisterState
    data class Registered(val message: String) : RegisterState
    data class Error(val throwable: Throwable) : RegisterState
}