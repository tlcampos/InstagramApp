package com.example.instagramapp.add.view

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.instagramapp.R
import com.example.instagramapp.add.Add
import com.example.instagramapp.common.base.BaseFragment
import com.example.instagramapp.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Add.View {

    override lateinit var presenter: Add.Presenter

    override fun setupPresenter() {
    }

    override fun setUpViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            getPermission.launch(REQUIRED_PERMISSION)
        }
    }

    private fun startCamera() {
        Log.i("Teste","startCamera")
    }

    private val getPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.permission_denied, Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(requireContext(), REQUIRED_PERMISSION) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA

    }

}