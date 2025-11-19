package com.alex.concertapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alex.concertapp.ui.home.HomeScreen
import com.alex.concertapp.ui.details.DetailsScreen
import com.alex.concertapp.ui.checkout.CheckoutScreen
import com.alex.concertapp.ui.confirmation.ConfirmationScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onConcertClick = { nav.navigate("details/$it") }
            )
        }
        composable("details/{id}") {
            DetailsScreen(
                onBuyClick = { nav.navigate("checkout/$it") }
            )
        }
        composable("checkout/{id}") {
            CheckoutScreen(
                onPurchaseDone = { nav.navigate("confirmation") }
            )
        }
        composable("confirmation") {
            ConfirmationScreen(onBackHome = { nav.navigate("home") })
        }
    }
}
