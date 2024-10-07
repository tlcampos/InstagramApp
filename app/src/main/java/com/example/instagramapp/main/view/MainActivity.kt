package com.example.instagramapp.main.view

import android.os.Build
import android.os.Bundle
import android.view.AbsSavedState
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.instagramapp.R
import com.example.instagramapp.camera.view.CameraFragment
import com.example.instagramapp.databinding.ActivityMainBinding
import com.example.instagramapp.extension.replaceFragment
import com.example.instagramapp.home.view.HomeFragment
import com.example.instagramapp.profile.view.ProfileFragment
import com.example.instagramapp.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var currentFragment: Fragment

    private lateinit var fragmentSavedState: HashMap<String, Fragment.SavedState?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        setSupportActionBar(binding.mainToolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        if (savedInstanceState == null){
            fragmentSavedState = HashMap()
        } else {
            savedInstanceState.getSerializable("fragmentState") as HashMap<String, Fragment.SavedState?>
        }

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }

    private fun setScrollToolbarEnabled(enabled: Boolean){
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.mainAppBar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled){
            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.mainAppBar.layoutParams = coordinatorParams

    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("fragmentState", fragmentSavedState)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnable = false

        val newFrag: Fragment? = when(item.itemId) {
            R.id.menu_bottom_home -> {
                HomeFragment()
            }
            R.id.menu_bottom_profile -> {
                ProfileFragment()
            }
            else -> null
        }

        val currFragment = supportFragmentManager.findFragmentById(R.id.main_fragment)

        val fragmentTag = newFrag?.javaClass?.simpleName

        if (!currFragment?.tag.equals(fragmentTag)) {
            currFragment?.let {  frag ->
                fragmentSavedState.put(
                    frag.tag!!,
                    supportFragmentManager.saveFragmentInstanceState(frag)
                )
            }
        }

        newFrag?.setInitialSavedState(fragmentSavedState[fragmentTag])
        newFrag?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, it, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit()
        }

        setScrollToolbarEnabled(scrollToolbarEnable)

        return true
    }
}