package com.androbrain.weathercaster.ui.screen.locations

import androidx.annotation.StringRes

data class LocationsState(
    val locations: List<LocationDisplayable> = emptyList(),
    val latitude: Double? = null,
    val longitude: Double? = null,
    val loadingLocation: Boolean = false,
    @StringRes val addError: Int? = null,
)
