package com.ivanovictin.weatherapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ivanovictin.weatherapp.features.home.ui.HomeRoute
import com.ivanovictin.weatherapp.features.home.ui.homeScreen

@Composable
fun Project3MobilityWeatherAppNavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        homeScreen()
    }
}
