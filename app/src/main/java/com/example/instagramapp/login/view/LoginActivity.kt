package com.example.instagramapp.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.common.base.DependencyInjector
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.ActivityLoginBinding
import com.example.instagramapp.login.Login
import com.example.instagramapp.login.presentation.LoginPresenter
import com.example.instagramapp.main.view.MainActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = LoginPresenter(this, DependencyInjector.loginRepository())

        with(binding) {
            loginEdtEmail.addTextChangedListener(watcher)
            loginEdtEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })
            loginEdtPassword.addTextChangedListener(watcher)
            loginEdtPassword.addTextChangedListener(TxtWatcher {
                displayPasswordFailure(null)
            })

            loginBtnEnter.setOnClickListener {
                presenter.login(loginEdtEmail.text.toString(), loginEdtPassword.text.toString())
            }

        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = binding.loginEdtEmail.text.toString().isNotEmpty()
                && binding.loginEdtPassword.text.toString().isNotEmpty()
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }
}