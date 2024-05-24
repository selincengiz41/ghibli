package com.selincengiz.ghibli.domain.repo

import com.selincengiz.ghibli.common.Resource

interface AuthRepo {
    suspend fun firebaseLogin(email: String, password: String): Resource<String>
    suspend fun firebaseRegister(email: String, password: String, name: String): Resource<String>
}