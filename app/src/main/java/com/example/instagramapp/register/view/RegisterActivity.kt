package com.example.instagramapp.register.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.instagramapp.R
import com.example.instagramapp.common.view.CropperImageFragment
import com.example.instagramapp.common.view.CropperImageFragment.Companion.KEY_URI
import com.example.instagramapp.databinding.ActivityRegisterBinding
import com.example.instagramapp.extension.hideKeyboard
import com.example.instagramapp.extension.replaceFragment
import com.example.instagramapp.main.view.MainActivity
import com.example.instagramapp.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.instagramapp.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.jvm.Throws

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var currentPhoto: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)
    }

    override fun goToNameAndPasswordScreen(email: String) {
        val fragment = RegisterNamePasswordFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EMAIL, email)
            }
        }

        replaceFragment(fragment)
    }

    override fun goToWelcomeScreen(name: String) {
        val fragment = RegisterWelcomeFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

    //Abrir Galeria
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                openImageCropper(uri)
            }
        }

    override fun goToGalleryScreen() {
        getContent.launch("image/*")
    }

    //Abrir a Camera
    private val getCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
            if (saved) {
                openImageCropper(currentPhoto)
            }
        }

    override fun goToCameraScreen() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {

            val photoFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Log.e("RegisterActivity", e.message, e)
                null
            }

            photoFile?.also {
                val photoUri =
                    FileProvider.getUriForFile(this, "com.example.instagramapp.fileprovider", it)
                currentPhoto = photoUri

                getCamera.launch(photoUri)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.register_fragment, fragment)
        hideKeyboard()
    }

    private fun openImageCropper(uri: Uri) {
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, uri)
            }
        }
        replaceFragment(fragment)
    }
}