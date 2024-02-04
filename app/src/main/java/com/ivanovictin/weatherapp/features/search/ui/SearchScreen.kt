package com.ivanovictin.weatherapp.features.search.ui

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ivanovictin.weatherapp.common.ui.SearchInputField
import com.ivanovictin.weatherapp.common.utils.ObserveEvent
import com.ivanovictin.weatherapp.designsystem.LocalDimens
import com.ivanovictin.weatherapp.features.search.ui.model.UIAutocompleteLocation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToWeatherScreen: (String) -> Unit,
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    ObserveEvent(
        eventsFlow = viewModel.eventsFlow,
        lifecycleOwner = LocalLifecycleOwner.current,
    ) {
        when (it) {
            is SearchEvent.NavigateToDestinationWeather -> onNavigateToWeatherScreen(it.destination)
        }
    }

    SearchContent(
        state = state.value,
        onQueryChanged = {
            viewModel.onAction(SearchAction.QueryChanged(it))
        },
        onDestinationSelected = {
            viewModel.onAction(SearchAction.DestinationSelected(it))
        },
        onSearchIconClicked = {
            viewModel.onAction(SearchAction.SearchTapped)
        })
}

@Composable
private fun LocationReccomendations(
    locations: List<UIAutocompleteLocation>,
    onDestinationSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(locations) { location ->
            Row(
                modifier = Modifier
                    .clickable {
                        onDestinationSelected(location.name)
                    }
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray)
                    .background(Color.White.copy(alpha = 0.5f))
                    .padding(LocalDimens.current.large),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = location.name)
            }
        }
    }
}

@Composable
fun SearchContent(
    state: SearchUiState,
    onSearchIconClicked: () -> Unit,
    onDestinationSelected: (String) -> Unit,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val localConfiguration = LocalConfiguration.current
    val screenHeight = remember { localConfiguration.screenHeightDp }
    val screenWidth = remember { localConfiguration.screenWidthDp }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background,
                    ),
                    tileMode = TileMode.Decal
                )
            )
    ) {
        AnimatedSearchInputField(
            state = state,
            screenWidth = screenWidth,
            isSearching = state.isSearching,
            onDestinationSelected = onDestinationSelected,
            onQueryChanged = onQueryChanged,
            focusRequester = focusRequester,
        )

        AnimatedSearchIcon(
            screenHeight = screenHeight,
            screenWidth = screenWidth,
            isSearching = state.isSearching,
            onSearchIconClicked = onSearchIconClicked,
        )
    }
}

@Composable
private fun AnimatedSearchIcon(
    screenHeight: Int,
    screenWidth: Int,
    isSearching: Boolean,
    onSearchIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var animationYOffset by remember {
        mutableFloatStateOf(
            (screenHeight).toFloat() / 2 - 60
        )
    }

    var animationXOffset by remember {
        mutableFloatStateOf(
            (screenWidth).toFloat() / 2 - 60
        )
    }

    var size by remember {
        mutableStateOf(120.dp)
    }

    LaunchedEffect(isSearching) {
        if (!isSearching) return@LaunchedEffect
        coroutineScope {
            launch {
                animate(
                    initialValue = (screenHeight / 2).toFloat() - 60,
                    targetValue = 10f,
                    animationSpec = tween(durationMillis = 800)
                ) { value: Float, _: Float ->
                    animationYOffset = value
                }
            }
            launch {
                animate(
                    initialValue = 120.dp.value,
                    targetValue = 40.dp.value,
                    animationSpec = tween(durationMillis = 800)
                ) { value: Float, _: Float ->
                    size = value.dp
                }
            }

            launch {
                animate(
                    initialValue = (screenWidth).toFloat() / 2 - 60,
                    targetValue = (screenWidth).toFloat() / 2 - 20,
                    animationSpec = tween(durationMillis = 800)
                ) { value: Float, _: Float ->
                    animationXOffset = value
                }
            }
            delay(800)
            launch {
                animate(
                    initialValue = (screenWidth / 2).toFloat() - 20,
                    targetValue = screenWidth.toFloat() - 40,
                    animationSpec = tween(durationMillis = 500)
                ) { value: Float, _: Float ->
                    animationXOffset = value
                }
            }
        }
    }

    Icon(
        modifier = modifier
            .offset(x = animationXOffset.dp, y = animationYOffset.dp)
            .size(size)
            .clickable {
                if (!isSearching) {
                    onSearchIconClicked()
                }
            },
        imageVector = Icons.Default.Search,
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = ""
    )
}

@Composable
private fun BoxScope.AnimatedSearchInputField(
    state: SearchUiState,
    screenWidth: Int,
    isSearching: Boolean,
    focusRequester: FocusRequester,
    onQueryChanged: (String) -> Unit,
    onDestinationSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchInputOpacity by remember {
        mutableFloatStateOf(0f)
    }

    var searchInputWidth by remember {
        mutableFloatStateOf(0f)
    }

    LaunchedEffect(isSearching) {
        if (!isSearching) return@LaunchedEffect
        coroutineScope {
            delay(800)
            launch {
                animate(
                    initialValue = 0f,
                    targetValue = screenWidth.toFloat(),
                    animationSpec = tween(durationMillis = 500)
                ) { value: Float, _: Float ->
                    searchInputWidth = value
                }
            }
            launch {
                animate(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 500)
                ) { value: Float, _: Float ->
                    searchInputOpacity = value
                }
            }
            delay(500)
            focusRequester.requestFocus()

        }
    }
    SearchInputField(
        modifier = modifier
            .align(Alignment.TopCenter)
            .width(searchInputWidth.dp)
            .focusRequester(focusRequester)
            .alpha(searchInputOpacity),
        query = state.query,
        onQueryChange = { onQueryChanged(it) },
        placeHolder = "",
        enabled = true,
        focusRequester = focusRequester
    )

    LocationReccomendations(
        modifier = Modifier.padding(top = 56.dp),
        locations = state.locations,
        onDestinationSelected = onDestinationSelected
    )

}