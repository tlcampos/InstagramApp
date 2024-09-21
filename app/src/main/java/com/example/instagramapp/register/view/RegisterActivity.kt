package com.example.instagramapp.register.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.R
import com.example.instagramapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val fragment = RegisterEmailFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.register_fragment, fragment)
            commit()
        }

    }
}