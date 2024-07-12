package com.example.instagramapp.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.R
import com.example.instagramapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginEdtEmailInput.error = "Esse e-mail é invalido"
    }
}