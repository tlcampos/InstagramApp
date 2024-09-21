package com.example.instagramapp.register.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagramapp.R
import com.example.instagramapp.common.base.DependencyInjector
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.FragmentRegisterEmailBinding
import com.example.instagramapp.register.RegisterEmail
import com.example.instagramapp.register.presentation.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding: FragmentRegisterEmailBinding? = null

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)

        binding?.let {
            with(it) {
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                registerBtnNext.setOnClickListener {
                    presenter.create(registerEdtEmail.text.toString())
                }

                registerEdtEmail.addTextChangedListener(watcher)
                registerEdtEmail.addTextChangedListener(TxtWatcher {
                    displayEmailFailure(null)
                })
            }
        }
    }
    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
    private val watcher = TxtWatcher {
        binding?.registerBtnNext?.isEnabled = binding?.registerEdtEmail?.text.toString().isNotEmpty()

    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.registerEdtEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding?.registerEdtEmailInput?.error = message
    }

    override fun goToNameAndPasswordScreen(email: String) {
        //Mandar para o proximo Fragment
    }


}