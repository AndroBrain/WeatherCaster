package com.androbrain.weathercaster.ui.screen.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.domain.core.fold
import com.androbrain.weathercaster.domain.forecast.GetForecastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getForecastsUseCase: GetForecastsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(LocationsState())
    val state = _state.asStateFlow()

    fun addLocation() {
        val currentState = state.value
        if (currentState.latitude == null) {
            _state.update { state -> state.copy(addError = R.string.locations_add_err_latitude_missing) }
            return
        }
        if (currentState.longitude == null) {
            _state.update { state -> state.copy(addError = R.string.locations_add_err_longitude_missing) }
            return
        }
        val id = UUID.randomUUID().toString()
        _state.update { state ->
            state.copy(
                locations = listOf(
                    LocationDisplayable.Loading(
                        id = id,
                        latitude = currentState.latitude,
                        longitude = currentState.longitude
                    )
                ) + state.locations
            )
        }
        getForecast(id = id, latitude = currentState.latitude, longitude = currentState.longitude)
    }

    fun setLatitude(latitudeString: String) {
        val latitude = latitudeString.toDoubleOrNull() ?: return
        _state.update { state -> state.copy(latitude = latitude, addError = null) }
    }

    fun setLongitude(longitudeString: String) {
        val longitude = longitudeString.toDoubleOrNull() ?: return
        _state.update { state -> state.copy(longitude = longitude, addError = null) }
    }

    fun setLoadingLocation(isLoading: Boolean) {
        _state.update { state -> state.copy(loadingLocation = isLoading) }
    }

    fun retry(location: LocationDisplayable) {
        _state.update { state ->
            val locations = state.locations.toMutableList()
            val index = locations.indexOfFirst { it.id == location.id }
            if (index < 0) {
                return@update state
            }
            locations[index] = LocationDisplayable.Loading(
                id = location.id,
                latitude = location.latitude,
                longitude = location.longitude,
            )
            state.copy(locations = locations)
        }
        getForecast(id = location.id, latitude = location.latitude, longitude = location.longitude)
    }

    private fun getForecast(
        id: String,
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            getForecastsUseCase(
                latitude = latitude,
                longitude = longitude,
            ).fold(
                onOk = {
                    _state.update { state ->
                        val locations = state.locations.toMutableList()
                        val index = locations.indexOfFirst { it.id == id }
                        if (index < 0) {
                            return@update state
                        }
                        locations[index] = LocationDisplayable.Ok(
                            id = id,
                            latitude = latitude,
                            longitude = longitude,
                            model = it.value,
                        )
                        state.copy(locations = locations)
                    }
                },
                onError = {
                    _state.update { state ->
                        val locations = state.locations.toMutableList()
                        val index = locations.indexOfFirst { it.id == id }
                        if (index < 0) {
                            return@update state
                        }
                        locations[index] = LocationDisplayable.Error(
                            id = id,
                            latitude = latitude,
                            longitude = longitude,
                            error = it.type,
                        )
                        state.copy(locations = locations)
                    }
                }
            )
        }
    }
}
