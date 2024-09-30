package com.example.instagramapp.camera.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagramapp.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    private var cameraBinding: FragmentCameraBinding? = null
    private val binding get() = cameraBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cameraBinding = FragmentCameraBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}
