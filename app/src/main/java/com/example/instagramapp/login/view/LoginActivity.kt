package com.example.instagramapp.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextEmail = binding.loginEdtEmail
        val editTextPassword = binding.loginEdtPassword

        editTextEmail.addTextChangedListener(watcher)
        editTextPassword.addTextChangedListener(watcher)

        val buttonEnter = binding.loginBtnEnter

        buttonEnter.setOnClickListener {
            buttonEnter.showProgress(true)

            binding.loginEdtEmailInput.error = "Esse e-mail é invalido"

            binding.loginEdtPasswordInput.error = "Senha incorreta"

            Handler(Looper.getMainLooper()).postDelayed({
                buttonEnter.showProgress(false)
            }, 2000)
        }

    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.loginBtnEnter.isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }
}