package com.ivanovictin.weatherapp.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics

@Composable
fun SearchInputField(
    query: String,
    onQueryChange: (String) -> Unit,
    placeHolder: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
) {
    TextField(
        modifier = modifier.focusRequester(focusRequester),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        enabled = enabled,
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(text = placeHolder) }
    )
}