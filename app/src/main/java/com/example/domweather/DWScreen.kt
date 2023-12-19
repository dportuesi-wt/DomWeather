package com.example.domweather

sealed class DWScreen(
    val route: String,
) {
    object Home : DWScreen("home")
}