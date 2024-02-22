package com.androbrain.weathercaster.ui.screen.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.ui.screen.forecast.composable.ForecastItem
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    viewModel: ForecastViewModel,
    navigateUp: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            id = R.string.location_label,
                            state.model.city,
                            state.model.latitude,
                            state.model.longitude,
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back),
                        )
                    }
                }
            )
        }
    ) { insets ->
        val itemPadding = PaddingValues(bottom = App.dimens.viewSpacingSmall)
        LazyVerticalGrid(
            modifier = Modifier.padding(insets),
            columns = GridCells.Adaptive(App.dimens.weatherItemMinSize),
            contentPadding = PaddingValues(horizontal = App.dimens.screenSpacing),
            horizontalArrangement = Arrangement.spacedBy(App.dimens.viewSpacingSmall),
        ) {
            val itemModifier = Modifier
                .fillMaxWidth()
                .padding(itemPadding)
            items(state.model.forecasts) { forecast ->
                ForecastItem(modifier = itemModifier, model = forecast)
            }
        }
    }
}
