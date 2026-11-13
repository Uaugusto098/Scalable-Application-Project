package com.example.skeletonapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.skeletonapp.Althea.view.SkeletonAppTheme
import com.example.skeletonapp.Althea.view.navigation.AppNavigation
import com.example.skeletonapp.mitrecasa.view.navigationMitre.AppNavigationMitre
import com.example.skeletonapp.naeem.View.navigationnaeem.AppNavigationNaeem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (BuildConfig.FLAVOR) {
            "naeem" -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        enableEdgeToEdge()
        setContent {
            SkeletonAppTheme {
                when (BuildConfig.FLAVOR) {
                    "althea" -> AppNavigation()
                    "mitre", "mitrecasa" -> AppNavigationMitre()
                    "naeem" -> AppNavigationNaeem()

                    else -> {

                    }
                }
            }
        }
    }
}
