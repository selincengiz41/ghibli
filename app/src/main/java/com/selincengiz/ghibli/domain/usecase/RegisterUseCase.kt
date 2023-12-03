package com.selincengiz.ghibli.domain.usecase

import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.repo.AuthRepo

class RegisterUseCase(private val authRepo: AuthRepo) {

    suspend operator fun invoke(
        email: String?,
        password: String?,
        passwordConfirm: String?,
        name: String?
    ): Resource<String> {
        if (email.isNullOrEmpty().not() && password.isNullOrEmpty()
                .not() && passwordConfirm.isNullOrEmpty().not() && name.isNullOrEmpty()
                .not()
        ) {
            if (password!!.length >= 6) {
                if (password.equals(passwordConfirm)) {
                    return authRepo.firebaseRegister(email!!, password!!, name!!)
                } else {
                    return Resource.Error(Exception("Passwords must be matched!"))
                }


            } else {
                return Resource.Error(Exception("Password must be at least 6 characters."))
            }

        } else {
            return Resource.Error(Exception("Please fill in the blanks."))

        }

    }
}