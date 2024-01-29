package com.ivanovictin.weatherapp.features.search.ui

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ivanovictin.weatherapp.R
import com.ivanovictin.weatherapp.common.ui.SearchInputField
import com.ivanovictin.weatherapp.designsystem.LocalDimens

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

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
        targetValue = if (state.isSearching) {
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
                enabled = state.isSearching,
                focusRequester = focusRequester
            )
        }
    }
}