package com.trends.gallery.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.trends.gallery.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragment_navigation_container) as NavHostFragment)
    }
    private val navController by lazy { navHostFragment.navController }
    private var searchView: SearchView? = null
    private var searchQuery: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Resplash_Theme_DayNight)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)
        lifecycle.addObserver(viewModel)
        initNavController()

    }

    private fun initNavController() {
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }




}
