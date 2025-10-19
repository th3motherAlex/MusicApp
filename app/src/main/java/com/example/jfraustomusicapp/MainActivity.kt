package com.example.jfraustomusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.jfraustomusicapp.nav.AlbumArg
import com.example.jfraustomusicapp.ui.detail.DetailScreen
import com.example.jfraustomusicapp.ui.home.HomeScreen
import com.example.jfraustomusicapp.ui.theme.AppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val nav = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = nav,
                        startDestination = "home",
                        enterTransition = { slideInHorizontally { 1000 } + fadeIn() },
                        exitTransition = { slideOutHorizontally { -1000 } + fadeOut() },
                        popEnterTransition = { slideInHorizontally { -1000 } + fadeIn() },
                        popExitTransition = { slideOutHorizontally { 1000 } + fadeOut() }
                    ) {
                        composable("home") {
                            HomeScreen(onAlbumClick = { id ->
                                nav.currentBackStackEntry?.savedStateHandle?.set("albumArg", AlbumArg(id))
                                nav.navigate("detail")
                            })
                        }
                        composable("detail") {
                            val arg = nav.previousBackStackEntry?.savedStateHandle?.get<AlbumArg>("albumArg")
                            DetailScreen(albumId = arg?.albumId ?: "")
                        }
                    }
                }
            }
        }
    }
}
