package com.selincengiz.ghibli.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //bottomNav settings
        bottomNav()
    }

    fun bottomNav() {
        with(binding) {

            //Bottom Navigation Menu
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment

            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
            navHostFragment.navController.addOnDestinationChangedListener { controller, destination, _ ->

                when (destination.id) {

                    R.id.searchFragment -> {
                        visibilityBottomNav = true


                    }


                    R.id.cartFragment -> {
                        visibilityBottomNav = true


                    }

                    R.id.favoritesFragment -> {
                        visibilityBottomNav = true

                    }

                    else -> {
                        visibilityBottomNav = false


                    }

                }


            }
        }
    }

}