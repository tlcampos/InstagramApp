package com.example.instagramapp.common.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.instagramapp.R
import com.example.instagramapp.databinding.FragmentImageCropperBinding
import java.io.File

class CropperImageFragment : Fragment(R.layout.fragment_image_cropper) {

    private var binding: FragmentImageCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentImageCropperBinding.bind(view)

        val uri = arguments?.getParcelable<Uri>(KEY_URI)

        binding?.let {
            with(it) {
                cropperContainner.setAspectRatio(1,1)
                cropperContainner.setFixedAspectRatio(true)
                cropperContainner.isAutoZoomEnabled = true

                cropperContainner.setImageUriAsync(uri)

                cropperBtnCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                cropperContainner.setOnCropImageCompleteListener { view, result ->
                    Log.i("Teste", "nova imagem: ${result.uri}")

                    setFragmentResult("cropKey", bundleOf(KEY_URI to result.uri))

                    parentFragmentManager.popBackStack()
                }

                cropperBtnSave.setOnClickListener {
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    if (dir != null) {
                        val uriToSaved = Uri.fromFile(File( dir.path,System.currentTimeMillis().toString() + ".jpeg"))
                        cropperContainner.saveCroppedImageAsync(uriToSaved)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_URI = "key_uri"
    }
}