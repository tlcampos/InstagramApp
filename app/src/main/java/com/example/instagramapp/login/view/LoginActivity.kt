package com.example.instagramapp.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            loginEdtEmail.addTextChangedListener(watcher)
            loginEdtPassword.addTextChangedListener(watcher)

            loginBtnEnter.setOnClickListener {
                loginBtnEnter.showProgress(true)

                loginEdtEmailInput.error = "Esse e-mail é invalido"
                loginEdtPasswordInput.error = "Senha incorreta"

                Handler(Looper.getMainLooper()).postDelayed({
                    loginBtnEnter.showProgress(false)
                }, 2000)
            }

        }
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = it.isNotEmpty()
    }
}