package com.ivanovictin.weatherapp.features.weather.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ivanovictin.weatherapp.R
import com.ivanovictin.weatherapp.designsystem.LocalDimens
import com.ivanovictin.weatherapp.designsystem.WeatherAppTheme
import com.ivanovictin.weatherapp.features.weather.ui.model.UiWeather

@Composable
fun HomeScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = state.value,
    )
}

@Composable
private fun HomeContent(
    uiState: WeatherUiState,
    modifier: Modifier = Modifier,
) {
    if (uiState.uiWeather != null) {
        Column(modifier = modifier) {
            WeatherContent(weatherResult = uiState.uiWeather)
        }
    }

}


@Composable
private fun WeatherContent(
    weatherResult: UiWeather,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                start = LocalDimens.current.large,
                end = LocalDimens.current.large,
                top = LocalDimens.current.large,
            ),
            text = weatherResult.location,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            modifier = Modifier.padding(
                horizontal = LocalDimens.current.large,
            ),
            text = weatherResult.country,
            style = MaterialTheme.typography.headlineSmall
        )
        Image(
            modifier = Modifier
                .padding(LocalDimens.current.large),
            painter = painterResource(id = weatherResult.weatherImage.drawableResId),
            contentDescription = "",
        )
        Text(
            text = weatherResult.currentTemperatureInCelsius,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black)
        )

        WeatherData(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = LocalDimens.current.large,
                    end = LocalDimens.current.medium,
                    start = LocalDimens.current.medium
                ),
            uiWeather = weatherResult
        )
    }
}

@Composable
private fun WeatherData(uiWeather: UiWeather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(LocalDimens.current.large),
            )
            .padding(LocalDimens.current.large),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            WeatherTextInfo(
                infoValue = uiWeather.timeOfDay.title,
                infoIconResId = uiWeather.timeOfDay.icon
            )
            WeatherTextInfo(
                infoValue = uiWeather.windSpeedInKmh,
                infoIconResId = R.drawable.ic_wind
            )
            WeatherTextInfo(
                infoValue = uiWeather.pressure,
                infoIconResId = R.drawable.ic_pressure
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalDimens.current.medium),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            WeatherTextInfo(
                infoValue = uiWeather.windDirection,
                infoIconResId = R.drawable.ic_wind_direction
            )
            WeatherTextInfo(
                infoValue = uiWeather.humidity,
                infoIconResId = R.drawable.ic_humidity
            )
        }
    }
}

@Composable
private fun WeatherTextInfo(
    infoValue: String,
    @DrawableRes infoIconResId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(LocalDimens.current.huge),
            painter = painterResource(id = infoIconResId),
            contentDescription = contentDescription
        )
        Spacer(modifier = Modifier.height(LocalDimens.current.large))
        Text(
            text = infoValue,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    WeatherAppTheme {
        Surface {
            HomeScreen()
        }
    }
}
