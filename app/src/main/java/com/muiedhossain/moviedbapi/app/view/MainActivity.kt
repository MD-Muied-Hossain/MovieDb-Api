package com.muiedhossain.moviedbapi.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.app.adapter.NowShowingMovieAdapter
import com.muiedhossain.moviedbapi.app.adapter.PopularMovieAdapter
import com.muiedhossain.moviedbapi.app.model.PopularMovieResult
import com.muiedhossain.moviedbapi.app.viewModel.NowShowingMovieViewModel
import com.muiedhossain.moviedbapi.app.viewModel.PopularMovieViewModel
import com.muiedhossain.moviedbapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawer)
        NavigationUI.setupWithNavController(binding.navgationView,navController)

        binding.root

    }

    fun openDrawer(view: View) {
        binding.drawer.openDrawer(GravityCompat.START)
    }
}