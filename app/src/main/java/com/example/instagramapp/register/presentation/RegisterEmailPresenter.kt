package com.example.instagramapp.register.presentation

import android.util.Patterns
import com.example.instagramapp.R
import com.example.instagramapp.common.model.UserAuth
import com.example.instagramapp.login.Login
import com.example.instagramapp.login.data.LoginCallback
import com.example.instagramapp.login.data.LoginRepository
import com.example.instagramapp.register.RegisterEmail
import com.example.instagramapp.register.data.RegisterEmailCallback
import com.example.instagramapp.register.data.RegisterEmailRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterEmailRepository
) : RegisterEmail.Presenter {

    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)

            repository.create(email, object : RegisterEmailCallback {
                override fun onSuccess() {
                    view?.goToNameAndPasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }

    }

    override fun onDestroy() {
        view = null
    }
}