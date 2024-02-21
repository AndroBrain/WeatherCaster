package com.androbrain.weathercaster.ui.screen.locations.composable

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun AddLocationItem(
    modifier: Modifier = Modifier,
    latitude: Double?,
    longitude: Double?,
    @StringRes addError: Int?,
    setLatitude: (String) -> Unit,
    setLongitude: (String) -> Unit,
    add: () -> Unit,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(App.dimens.viewSpacingSmall)
                .animateContentSize(),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = latitude?.toString().orEmpty(),
                onValueChange = setLatitude,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                label = { Text(text = stringResource(id = R.string.locations_add_latitude)) }
            )
            Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
            val focusManager = LocalFocusManager.current
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = longitude?.toString().orEmpty(),
                onValueChange = setLongitude,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        add()
                        focusManager.clearFocus()
                    }
                ),
                label = { Text(text = stringResource(id = R.string.locations_add_longitude)) }
            )
            Spacer(modifier = Modifier.height(App.dimens.viewSpacingMedium))
            addError?.let { error ->
                Text(text = stringResource(id = error), color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
            }
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    add()
                    focusManager.clearFocus()
                },
            ) {
                Text(text = stringResource(id = R.string.locations_add))
            }
        }
    }
}
