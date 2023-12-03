package com.selincengiz.ghibli.domain.usecase

import android.widget.Toast
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.repo.AuthRepo

class LoginUseCase(private val authRepo: AuthRepo) {

    suspend operator fun invoke(
        email: String?,
        password: String?
    ): Resource<String> {
        if (email.isNullOrEmpty().not() && password.isNullOrEmpty().not()) {
            if (password!!.length >= 6) {
                return authRepo.firebaseLogin(email!!, password!!)

            } else {
                return Resource.Error(Exception("Password must be at least 6 characters."))
            }

        } else {
            return Resource.Error(Exception("Please fill in the blanks."))

        }

    }
}