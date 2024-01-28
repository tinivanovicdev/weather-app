package com.ivanovictin.weatherapp.features.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeScreen() {
    composable(HomeRoute) {
        HomeScreen()
    }
}

@SuppressWarnings("TopLevelPropertyNaming")
const val HomeRoute = "home"
