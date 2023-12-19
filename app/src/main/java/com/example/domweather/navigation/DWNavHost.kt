package com.example.domweather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domweather.DWScreen
import com.example.domweather.ui.home.HomeScreenRoute
import org.koin.androidx.compose.getViewModel


@Composable
fun DWNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = DWScreen.Home.route,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(DWScreen.Home.route) {
            HomeScreenRoute(
                homeViewModel = getViewModel()
            )
        }
    }
}