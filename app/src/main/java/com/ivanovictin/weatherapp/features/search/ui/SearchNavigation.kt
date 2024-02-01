package com.ivanovictin.weatherapp.features.search.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchScreen(onNavigateToWeatherScreen: (String) -> Unit) {
    composable(SearchRoute) {
        SearchScreen(onNavigateToWeatherScreen = onNavigateToWeatherScreen)
    }
}

@SuppressWarnings("TopLevelPropertyNaming")
const val SearchRoute = "search"