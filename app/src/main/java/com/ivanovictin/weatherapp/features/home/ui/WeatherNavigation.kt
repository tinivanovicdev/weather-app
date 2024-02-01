package com.ivanovictin.weatherapp.features.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ivanovictin.weatherapp.common.utils.NavigationUtils.DESTINATION

//route = "$TakePhotoRoute?$MEDIA_ID={$MEDIA_ID}&$IS_EDIT={$IS_EDIT}&$SOURCE={$SOURCE}",
fun NavGraphBuilder.homeScreen() {
    composable(
        route = "$HomeRoute?$DESTINATION={$DESTINATION}",
        arguments = listOf(navArgument(DESTINATION) {
            type = NavType.StringType
            nullable = false
        })
    ) {
        HomeScreen()
    }
}

@SuppressWarnings("TopLevelPropertyNaming")
const val HomeRoute = "home"
