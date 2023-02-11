package com.muiedhossain.moviedbapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.muiedhossain.moviedbapi.R
import com.muiedhossain.moviedbapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.container)

        appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawer)
        NavigationUI.setupWithNavController(binding.navgationView,navController)

        binding.root

    }

    fun openDrawer(view: View) {
        binding.drawer.openDrawer(GravityCompat.START)
    }
}