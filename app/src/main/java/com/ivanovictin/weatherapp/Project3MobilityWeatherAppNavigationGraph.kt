package com.ivanovictin.weatherapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ivanovictin.weatherapp.common.utils.NavigationUtils.DESTINATION
import com.ivanovictin.weatherapp.features.weather.ui.HomeRoute
import com.ivanovictin.weatherapp.features.weather.ui.homeScreen
import com.ivanovictin.weatherapp.features.search.ui.SearchRoute
import com.ivanovictin.weatherapp.features.search.ui.searchScreen

@Composable
fun Project3MobilityWeatherAppNavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SearchRoute,
        modifier = modifier,
    ) {
        homeScreen()
        searchScreen(onNavigateToWeatherScreen = { destination ->
            navController.navigate("$HomeRoute?${DESTINATION}=$destination")
        }
        )
    }
}
