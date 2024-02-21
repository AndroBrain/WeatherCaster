package com.androbrain.weathercaster.ui.screen.locations.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.ui.screen.locations.LocationDisplayable
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    location: LocationDisplayable,
    onClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
                .padding(App.dimens.viewSpacingSmall),
        ) {
            Text(text = stringResource(id = R.string.locations_latitude, location.latitude))
            Text(text = stringResource(id = R.string.locations_longitude, location.longitude))
        }
    }
}
