package com.selincengiz.ghibli.presentation


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.translation.ViewTranslationCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.common.IsFullScreen.isFullScreen
import com.selincengiz.ghibli.databinding.ActivityMainBinding
import com.selincengiz.ghibli.ui.ClickableMotionLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        bottomNav()





        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                // invoke required action
                if (currentId == R.id.collapsed) {


                    isFullScreen = false
                    binding.motionLayout.setTransition(R.id.collapsed, R.id.gone);
                    binding.motionLayout.progress = 0f
                    return
                }
                if (currentId == R.id.expanded) {

                    isFullScreen = true

                }
                if (currentId == R.id.gone) {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ViewFragment()).commit()
                }

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {


            }
        })

    }

    override fun onBackPressed() {
        // Eğer detay ekranındaysa, liste ekranına geri dön
        if (isFullScreen) {
            binding.motionLayout.transitionToEnd()

        } else {
            // Detay ekranı kapalıysa, varsayılan davranışı uygula (örneğin, aktiviteyi sonlandır)
            super.onBackPressed()
        }
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

                    R.id.detailFragment -> {
                        visibilityBottomNav = false

                    }


                    R.id.favoriteFragment -> {
                        visibilityBottomNav = true


                    }

                    R.id.seekFragment -> {
                        visibilityBottomNav = true


                    }

                    R.id.profileFragment2 -> {
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