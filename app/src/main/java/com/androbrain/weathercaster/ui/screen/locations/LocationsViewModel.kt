package com.androbrain.weathercaster.ui.screen.locations

import androidx.lifecycle.ViewModel
import com.androbrain.weathercaster.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LocationsViewModel @Inject constructor(

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
        _state.update { state ->
            state.copy(
                locations = state.locations + LocationDisplayable(
                    latitude = currentState.latitude,
                    longitude = currentState.longitude,
                ),
                latitude = null,
                longitude = null,
            )
        }
    }

    fun setLatitude(latitudeString: String) {
        val latitude = latitudeString.toDoubleOrNull() ?: return
        _state.update { state -> state.copy(latitude = latitude, addError = null) }
    }

    fun setLongitude(longitudeString: String) {
        val longitude = longitudeString.toDoubleOrNull() ?: return
        _state.update { state -> state.copy(longitude = longitude, addError = null) }
    }
}
