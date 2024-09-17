package com.example.instagramapp.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.ActivityLoginBinding
import com.example.instagramapp.login.Login

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            loginEdtEmail.addTextChangedListener(watcher)
            loginEdtPassword.addTextChangedListener(watcher)

            loginBtnEnter.setOnClickListener {
                //Chamar o Presenter
//                Handler(Looper.getMainLooper()).postDelayed({
//                    loginBtnEnter.showProgress(false)
//                }, 2000)
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