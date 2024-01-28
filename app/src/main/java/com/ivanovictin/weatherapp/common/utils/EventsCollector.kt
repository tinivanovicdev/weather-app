package com.ivanovictin.weatherapp.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveEvent(
    eventsFlow: Flow<T>,
    lifecycleOwner: LifecycleOwner,
    onEventCollected: (T) -> Unit,
) {
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                eventsFlow.collect {
                    onEventCollected(it)
                }
            }
        }
    }
}
