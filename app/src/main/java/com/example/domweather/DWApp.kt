package com.example.domweather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.domweather.navigation.DWNavHost

@Composable
fun DWApp(navHostController: NavHostController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        DWNavHost(
            modifier = Modifier,
            navController = navHostController,
            startDestination = DWScreen.Home.route
        )
    }
}