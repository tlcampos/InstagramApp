package com.example.instagramapp.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagramapp.R
import com.example.instagramapp.common.base.DependencyInjector
import com.example.instagramapp.common.util.TxtWatcher
import com.example.instagramapp.databinding.FragmentRegisterNamePasswordBinding
import com.example.instagramapp.databinding.FragmentRegisterWelcomeBinding
import com.example.instagramapp.register.RegisterNameAndPassword
import com.example.instagramapp.register.presentation.RegisterNameAndPasswordPresenter

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

    private var binding: FragmentRegisterWelcomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME) ?: IllegalArgumentException("Name not found")

        binding?.let {
            with(it) {
                registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener{
                    fragmentAttachListener?.goToPhotoScreen()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_NAME = "key_name"
    }
}