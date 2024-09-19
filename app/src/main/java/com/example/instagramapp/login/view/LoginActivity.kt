package com.example.instagramapp.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.ActivityLoginBinding
import com.example.instagramapp.login.Login
import com.example.instagramapp.login.view.presentation.LoginPresenter

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = LoginPresenter(this)

        with(binding) {
            loginEdtEmail.addTextChangedListener(watcher)
            loginEdtPassword.addTextChangedListener(watcher)

            loginBtnEnter.setOnClickListener {
                presenter.login(loginEdtEmail.text.toString(), loginEdtPassword.text.toString())
            }

        }
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = it.isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEdtEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEdtPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        //Ir para tela principal
    }

    override fun onUserUnauthorized() {
        //Mostar Alerta
    }
}