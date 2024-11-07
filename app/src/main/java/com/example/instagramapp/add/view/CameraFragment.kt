package com.example.instagramapp.add.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.instagramapp.R
import com.example.instagramapp.common.util.Files

class CameraFragment : Fragment() {

    private var imageCapture: ImageCapture? = null

    private lateinit var previewView: PreviewView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewView = view.findViewById(R.id.camera_img)
        view.findViewById<View>(R.id.camera_img_picture).setOnClickListener {
            takePhoto()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("camerakey"){requestKey, bundle ->
            val shouldStart = bundle.getBoolean("startCamera")

            if (shouldStart) {
                startCamera()
            }
        }
    }
    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = Files.generateFile(requireActivity())

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val saveUri = Uri.fromFile(photoFile)
                    Log.d("Teste", saveUri.toString())
                    setFragmentResult("takePhotoKey", bundleOf("uri" to saveUri))
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("Error", "Failure to take picture", exception)
                }

            })
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({

            val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .setTargetResolution(Size(480, 480))
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

            } catch (e: Exception){
                Log.e("Error", "Failure initialize camera", e)
            }

        }, getMainExecutor(requireContext()))
    }
}
