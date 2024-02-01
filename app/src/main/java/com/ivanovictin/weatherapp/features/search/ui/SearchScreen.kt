package com.ivanovictin.weatherapp.features.search.ui

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ivanovictin.weatherapp.R
import com.ivanovictin.weatherapp.common.ui.SearchInputField
import com.ivanovictin.weatherapp.common.utils.ObserveEvent
import com.ivanovictin.weatherapp.designsystem.LocalDimens
import com.ivanovictin.weatherapp.features.search.ui.model.UIAutocompleteLocation

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
        onTextInputInitiated = {
            viewModel.onAction(SearchAction.SearchTapped)
        })
}

@Composable
fun SearchContent(
    state: SearchUiState,
    onQueryChanged: (String) -> Unit,
    onDestinationSelected: (String) -> Unit,
    onTextInputInitiated: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val localConfiguration = LocalConfiguration.current
    val screenHeight = remember { localConfiguration.screenHeightDp + 500 }

    val offset by animateIntOffsetAsState(
        targetValue = if (state.wasSearchingInitiated) {
            IntOffset(x = 0, y = 200)
        } else {
            IntOffset(x = 0, y = screenHeight)
        }, label = "offset"
    )

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background,
                    ),
                    tileMode = TileMode.Decal
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.offset { offset }) {
            SearchInputField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(LocalDimens.current.medium)
                    .clip(CircleShape),
                query = state.query,
                onQueryChange = onQueryChanged,
                onClicked = onTextInputInitiated,
                placeHolder = stringResource(id = R.string.enter_a_destination),
                enabled = state.wasSearchingInitiated,
                focusRequester = focusRequester
            )
            LocationReccomendations(
                locations = state.locations,
                onDestinationSelected = onDestinationSelected
            )
        }
    }
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