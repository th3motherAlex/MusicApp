package com.jfrausto.musicapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.jfrausto.musicapp.ui.*
import com.jfrausto.musicapp.ui.theme.AppTheme
import com.jfrausto.musicapp.nav.AlbumArg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val nav = rememberNavController()
                NavHost(nav, startDestination = "home") {
                    composable("home") {
                        val vm: HomeViewModel = viewModel()
                        HomeScreen(vm) { id ->
                            nav.currentBackStackEntry?.savedStateHandle?.set("albumArg", AlbumArg(id))
                            nav.navigate("detail")
                        }
                    }
                    composable("detail") {
                        val arg = nav.previousBackStackEntry?.savedStateHandle?.get<AlbumArg>("albumArg")!!
                        val vm: DetailViewModel = viewModel()
                        DetailScreen(vm, arg.id) { nav.popBackStack() }
                    }
                }
            }
        }
    }
}
