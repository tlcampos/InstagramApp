package com.example.instagramapp.add.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagramapp.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private var galleryBinding: FragmentGalleryBinding? = null
    private val binding get() = galleryBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryBinding = FragmentGalleryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}
