package com.androbrain.weathercaster.ui.screen.forecast

import androidx.lifecycle.ViewModel
import com.androbrain.weathercaster.ui.screen.forecast.details.ForecastDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ForecastViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(ForecastDetailsState())
    val state = _state.asStateFlow()

}
