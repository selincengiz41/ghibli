package com.selincengiz.ghibli.ui


import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.translation.ViewTranslationCallback
import androidx.appcompat.app.ActionBar.NavigationMode
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.ViewTransitionController
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.selincengiz.ghibli.R
import com.selincengiz.ghibli.databinding.ActivityMainBinding
import com.selincengiz.ghibli.ui.video.VideoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

   // binding.fragmentContainer.visibility=View.GONE
        //bottomNav settings
        bottomNav()


        binding.motionLayout.setTransitionListener(object: MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                // invoke required action
                if (currentId==R.id.collapsed){
                    binding.visibilityBottomNav = true
                    binding.bottomNavigationView.requestLayout()

                }
                if (currentId==R.id.expanded){
                    binding.visibilityBottomNav = false
                    binding.bottomNavigationView.requestLayout()


                }

            }
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {


            }
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {

            }
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
                if (triggerId==R.id.collapsed){
                    binding.visibilityBottomNav = true
                    binding.bottomNavigationView.requestLayout()

                }
                if (triggerId==R.id.expanded){
                    binding.visibilityBottomNav = false
                    binding.bottomNavigationView.requestLayout()



                }
            }
        })

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
                    R.id.detailFragment ->{
                        visibilityBottomNav=false

                    }


                    R.id.favoriteFragment -> {
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