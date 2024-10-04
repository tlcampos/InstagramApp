package com.example.instagramapp.main.view

import android.os.Build
import android.os.Bundle
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

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()
        profileFragment = ProfileFragment()

        currentFragment = homeFragment

        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_fragment, profileFragment, "3").hide(profileFragment)
            add(R.id.main_fragment, cameraFragment, "2").hide(cameraFragment)
            add(R.id.main_fragment, searchFragment, "1").hide(searchFragment)
            add(R.id.main_fragment, homeFragment, "0")
            commit()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnable = false
        when (item.itemId){
            R.id.menu_bottom_home ->{
                if (currentFragment == homeFragment) return false
                currentFragment?.let { supportFragmentManager.beginTransaction().hide(it).show(homeFragment).commit() }
                currentFragment = homeFragment
            }
            R.id.menu_bottom_search ->{
                if (currentFragment == searchFragment) return false
                currentFragment?.let { supportFragmentManager.beginTransaction().hide(it).show(searchFragment).commit() }
                currentFragment = searchFragment
            }
            R.id.menu_bottom_add->{
                if (currentFragment == cameraFragment) return false
                currentFragment?.let { supportFragmentManager.beginTransaction().hide(it).show(cameraFragment).commit() }
                currentFragment = cameraFragment
            }
            R.id.menu_bottom_profile ->{
                if (currentFragment == profileFragment) return false
                currentFragment?.let { supportFragmentManager.beginTransaction().hide(it).show(profileFragment).commit() }
                currentFragment = profileFragment
                scrollToolbarEnable = true
            }
        }

        setScrollToolbarEnabled(scrollToolbarEnable)

//        currentFragment?.let{
//            replaceFragment(R.id.main_fragment, it)
//        }
        return true
    }
}