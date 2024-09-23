package com.example.instagramapp.register.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagramapp.R
import com.example.instagramapp.common.base.DependencyInjector
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.FragmentRegisterNamePasswordBinding
import com.example.instagramapp.register.RegisterNameAndPassword
import com.example.instagramapp.register.presentation.RegisterNameAndPasswordPresenter

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password),
    RegisterNameAndPassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    override lateinit var presenter: RegisterNameAndPassword.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterNameAndPasswordPresenter(this, repository)

        val email = arguments?.getString(KEY_EMAIL) ?: IllegalArgumentException("email not found")

        binding?.let {
            with(it) {
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                registerNameBtnNext.setOnClickListener {
                    presenter.     create(
                        email.toString(),
                        registerEdtName.text.toString(),
                        registerEdtPassword.text.toString(),
                        registerEdtConfirm.text.toString()
                    )
                }

                registerEdtName.addTextChangedListener(watcher)
                registerEdtPassword.addTextChangedListener(watcher)
                registerEdtConfirm.addTextChangedListener(watcher)

                registerEdtName.addTextChangedListener(TxtWatcher {
                    displayNameFailure(null)
                })
                registerEdtPassword.addTextChangedListener(TxtWatcher {
                    displayPasswordFailure(null)
                })
                registerEdtConfirm.addTextChangedListener(TxtWatcher {
                    displayPasswordFailure(null)
                })

            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.registerEdtNameInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.registerEdtPasswordInput?.error = passwordError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    override fun onCreateSuccess(name: String) {
        //TODO: abrir a tela de bem vindo
    }

    private val watcher = TxtWatcher {
        binding?.registerNameBtnNext?.isEnabled = binding?.registerEdtName?.text.toString().isNotEmpty()
                && binding?.registerEdtPassword?.text.toString().isNotEmpty()
                && binding?.registerEdtConfirm?.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }
}