package com.ivanovictin.weatherapp.features.search.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchScreen() {
    composable(SearchRoute) {
        SearchScreen()
    }
}

@SuppressWarnings("TopLevelPropertyNaming")
const val SearchRoute = "search"